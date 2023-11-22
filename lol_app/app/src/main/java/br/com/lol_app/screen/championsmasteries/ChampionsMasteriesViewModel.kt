package br.com.lol_app.screen.championsmasteries

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.R
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.domain.model.summoner.SummonerBaseData
import br.com.lol_app.domain.repositories.champion.ChampionRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChampionsMasteriesViewModel @Inject constructor(private val championRepository: ChampionRepositoryContract) :
    BaseViewModel() {

    private val _championsMasteries = MutableLiveData<List<ChampionBaseData>?>()
    val championsMasteries: LiveData<List<ChampionBaseData>?> get() = _championsMasteries

    val slideUpAnimation: LiveData<Pair<Animation, Animation>> get() = _slideUpAnimation
    private val _slideUpAnimation = MutableLiveData<Pair<Animation, Animation>>()

    val slideDownAnimation: LiveData<Pair<Animation, Animation>> get() = _slideDownAnimation
    private val _slideDownAnimation = MutableLiveData<Pair<Animation, Animation>>()

    private val _summonerBaseData = MutableLiveData<SummonerBaseData>()
    val summonerBaseData: LiveData<SummonerBaseData> get() = _summonerBaseData

    fun fetchChampionsMasteries(summonerId: String) {
        defaultLaunch {
            val response = championRepository.fetchChampionsMasteries(summonerId)
            if (!response.isNullOrEmpty()) {
                _championsMasteries.postValue(response)
            }
        }
    }

    fun setListAnimation(isVisible: Boolean, context: Context) {
        if (isVisible) {
            val listAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_up)
            val iconAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_up_to_down)
            _slideUpAnimation.postValue(Pair(listAnimation, iconAnimation))
        } else {
            val listAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_down)
            val iconAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_down_to_up)
            _slideDownAnimation.postValue(Pair(listAnimation, iconAnimation))
        }
    }
}