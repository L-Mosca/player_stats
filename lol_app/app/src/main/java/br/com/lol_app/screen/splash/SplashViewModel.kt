package br.com.lol_app.screen.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _showHomeScreen = MutableLiveData<Unit>()
    val showHomeScreen: LiveData<Unit> get() = _showHomeScreen

    fun loadingSplashData() {
        Handler(Looper.getMainLooper()).postDelayed({
           _showHomeScreen.postValue(Unit)
        }, 2000)
    }
}