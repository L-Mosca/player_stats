package br.com.lol_app.screen.maintier

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse
import br.com.lol_app.domain.repositories.user.SummonerRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainTierViewModel @Inject constructor(private val summonerRepository: SummonerRepositoryContract) :
    BaseViewModel() {

    private val _mainTierData = MutableLiveData<SummonerMainTierResponse>()
    val mainTierData: LiveData<SummonerMainTierResponse?> get() = _mainTierData

    fun fetchMainTierData(summonerId: String) {
        defaultLaunch {
            val response = summonerRepository.fetchSummonerMainTier(summonerId)
            if (response != null) _mainTierData.postValue(response!!)
        }
    }

}