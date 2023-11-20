package br.com.lol_app.screen.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.domain.repositories.champion.ChampionRepositoryContract
import br.com.lol_app.utils.getChampionNameById
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val championRepository: ChampionRepositoryContract) :
    BaseViewModel() {

    private val _freeChampionList = MutableLiveData<List<String>>()
    val freeChampionList: LiveData<List<String>> get() = _freeChampionList

    private val _freeChampionsForNewPlayers = MutableLiveData<List<String>>()
    val freeChampionsForNewPlayers: LiveData<List<String>> get() = _freeChampionsForNewPlayers

    fun fetchFreeChampionsRotation(context: Context) {
        defaultLaunch {
            val freeChampionsIds = championRepository.fetchFreeChampionsRotation()
            _freeChampionList.postValue(freeChampionsIds?.championIds.getChampionNameById(context))
            _freeChampionsForNewPlayers.postValue(
                freeChampionsIds?.championIdsForNewPlayers.getChampionNameById(
                    context
                )
            )
        }
    }

}