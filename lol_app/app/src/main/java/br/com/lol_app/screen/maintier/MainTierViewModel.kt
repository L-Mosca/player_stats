package br.com.lol_app.screen.maintier

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse
import br.com.lol_app.domain.repositories.user.SummonerRepositoryContract
import br.com.lol_app.screen.summonertier.SummonerTierFragment
import br.com.lol_app.utils.QUEUE_RANKED_FLEX_5X5
import br.com.lol_app.utils.QUEUE_RANKED_SOLO_5X5
import br.com.lol_app.utils.updateTier
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainTierViewModel @Inject constructor(private val summonerRepository: SummonerRepositoryContract) :
    BaseViewModel() {

    private val _mainTierData = MutableLiveData<List<SummonerMainTierResponse?>>()
    val mainTierData: LiveData<List<SummonerMainTierResponse?>> get() = _mainTierData

    fun fetchMainTierData(summonerId: String) {
        defaultLaunch {
            val response = summonerRepository.fetchSummonerMainTier(summonerId)
            _mainTierData.postValue(response)
        }
    }

    fun getFragmentsList(data: List<SummonerMainTierResponse?>): List<Fragment> {
        val fragmentList = mutableListOf<Fragment>()
        val tierList = mutableListOf<SummonerMainTierResponse?>()

        data.forEach {
            if (it != null) {
                tierList.add(it)
            }
        }

        val soloDuoData = tierList.find { it?.queueType == QUEUE_RANKED_SOLO_5X5 }
        val flexData = tierList.find { it?.queueType == QUEUE_RANKED_FLEX_5X5 }

        if (soloDuoData != null) {
            updateSummonerTier(soloDuoData.summonerId, soloDuoData)
        } else if (flexData != null) {
            updateSummonerTier(flexData.summonerId, flexData)
        }

        fragmentList.add(SummonerTierFragment.newInstance(soloDuoData))
        fragmentList.add(SummonerTierFragment.newInstance(flexData))

        return fragmentList
    }

    /**
     * If has valid summoner id and tier data, update user tier and date in local database
     */
    private fun updateSummonerTier(summonerId: String?, tierData: SummonerMainTierResponse) {
        defaultLaunch {
            if (!isValidTierData(summonerId, tierData)) {
                return@defaultLaunch
            } else {
                val summoner = summonerRepository.fetchRecentSummonerById(summonerId!!)
                summonerRepository.updateSummonerData(summoner.updateTier(tierData))
            }
        }
    }

    private fun isValidTierData(summonerId: String?, tierData: SummonerMainTierResponse) =
        summonerId.isNullOrEmpty() || tierData.rank.isNullOrEmpty() || tierData.tier.isNullOrEmpty() || tierData.leaguePoints != null

}