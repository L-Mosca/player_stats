package br.com.lol_app.screen.summonerdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.domain.model.summoner.SummonerBaseData
import br.com.lol_app.domain.model.summoner.SummonerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SummonerDetailViewModel @Inject constructor() :
    BaseViewModel() {


    private val _summonerBaseData = MutableLiveData<SummonerBaseData>()
    val summonerBaseData: LiveData<SummonerBaseData> get() = _summonerBaseData

    fun fetchSummonerStats(summonerResponse: SummonerResponse) {
        with(summonerResponse) {
            if (!name.isNullOrEmpty() && summonerLevel != null && profileIconId != null) {
                val summonerBaseData = SummonerBaseData(
                    name = name,
                    level = summonerLevel,
                    icon = profileIconId
                )
                _summonerBaseData.postValue(summonerBaseData)
            }
        }
    }
}