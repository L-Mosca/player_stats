package br.com.lol_app.screen.host

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.R
import br.com.lol_app.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _navGraphId = MutableLiveData<Int>()
    val navGraphId: LiveData<Int> get() = _navGraphId

    val showNavBottom: LiveData<Boolean> get() = _showNavBottom
    private val _showNavBottom = MutableLiveData<Boolean>()

    fun showHomeScreen() {
        _navGraphId.value = R.id.home_nav_graph
    }

    fun showSettingsScreen() {
        _navGraphId.value = R.id.settings_nav_graph
    }

    fun showStatsScreen() {
        _navGraphId.value = R.id.stats_nav_graph
    }

    fun showNavBottom(showNavBottom: Boolean) {
        _showNavBottom.postValue(showNavBottom)
    }

    fun getDestinationItemId(): Int {
        return when (_navGraphId.value) {
            R.id.home_nav_graph -> R.id.menuHome
            R.id.stats_nav_graph -> R.id.menuStats
            R.id.settings_nav_graph -> R.id.menuSettings
            else -> R.id.menuHome
        }
    }
}