package br.com.lol_app.screen.home

import androidx.lifecycle.MutableLiveData
import br.com.lol_app.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    val test = MutableLiveData<String>()

    fun test() {
        test.postValue("TESTE DESSE KACETE")
    }

}