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

        fragmentList.add(SummonerTierFragment.newInstance(soloDuoData))
        fragmentList.add(SummonerTierFragment.newInstance(flexData))

        return fragmentList
    }

}