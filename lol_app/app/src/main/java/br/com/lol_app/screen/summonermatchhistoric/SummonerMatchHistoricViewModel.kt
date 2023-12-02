package br.com.lol_app.screen.summonermatchhistoric

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.domain.repositories.match.MatchRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SummonerMatchHistoricViewModel @Inject constructor(private val matchRepository: MatchRepositoryContract) :
    BaseViewModel() {

    val test = MutableLiveData<String>()

    private val _noHistoricFound = MutableLiveData<Unit?>()
    val noHistoricFound: LiveData<Unit?> get() = _noHistoricFound

    fun fetchLastMatchesHistoric(summonerPUUId: String) {
        defaultLaunch {
            val response = matchRepository.fetchLastMatchesIDs(summonerPUUId)

            var string = response.toString()

            if (response.isNullOrEmpty()) {
                _noHistoricFound.postValue(Unit)
                return@defaultLaunch
            }

            test.postValue(string)

        }
    }
}