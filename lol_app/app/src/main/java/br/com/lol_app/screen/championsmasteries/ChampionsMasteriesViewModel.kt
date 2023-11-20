package br.com.lol_app.screen.championsmasteries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.domain.repositories.champion.ChampionRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChampionsMasteriesViewModel @Inject constructor(private val championRepository: ChampionRepositoryContract) :
    BaseViewModel() {

    private val _championsMasteries = MutableLiveData<List<ChampionBaseData>?>()
    val championsMasteries: LiveData<List<ChampionBaseData>?> get() = _championsMasteries

    fun fetchChampionsMasteries(summonerId: String) {
        defaultLaunch {
            val response = championRepository.fetchChampionsMasteries(summonerId)
            if (!response.isNullOrEmpty()) {
                _championsMasteries.postValue(response)
            }
        }
    }
}