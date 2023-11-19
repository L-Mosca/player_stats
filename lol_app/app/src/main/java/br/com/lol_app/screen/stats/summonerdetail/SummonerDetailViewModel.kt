package br.com.lol_app.screen.stats.summonerdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.domain.model.summoner.SummonerBaseData
import br.com.lol_app.domain.model.summoner.SummonerResponse
import br.com.lol_app.domain.repositories.champion.ChampionRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SummonerDetailViewModel @Inject constructor(private val championRepository: ChampionRepositoryContract) :
    BaseViewModel() {


    private val _summonerBaseData = MutableLiveData<SummonerBaseData>()
    val summonerBaseData: LiveData<SummonerBaseData> get() = _summonerBaseData

    private val _championsMasteries = MutableLiveData<List<ChampionBaseData>?>()
    val championsMasteries: LiveData<List<ChampionBaseData>?> get() = _championsMasteries

    fun fetchSummonerStats(summonerResponse: SummonerResponse) {
        with(summonerResponse) {
            if (!name.isNullOrEmpty() && summonerLevel != null && profileIconId != null) {
                val summonerBaseData = SummonerBaseData(
                    name = name,
                    level = summonerLevel,
                    icon = profileIconId
                )
                _summonerBaseData.postValue(summonerBaseData)
                fetchChampionsMasteries(id ?: "")
            }
        }
    }

    private fun fetchChampionsMasteries(summonerId: String) {
        defaultLaunch {
            val response = championRepository.fetchChampionsMasteries(summonerId)
            if (!response.isNullOrEmpty()) {
                _championsMasteries.postValue(response)
            }
        }
    }

}