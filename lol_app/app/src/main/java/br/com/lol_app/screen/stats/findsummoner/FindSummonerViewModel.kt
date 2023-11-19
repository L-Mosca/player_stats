package br.com.lol_app.screen.stats.findsummoner

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.R
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.domain.model.summoner.SummonerResponse
import br.com.lol_app.domain.repositories.user.SummonerRepositoryContract
import com.facebook.stetho.server.http.HttpStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class FindSummonerViewModel @Inject constructor(private val summonerRepository: SummonerRepositoryContract) :
    BaseViewModel() {

    val summonerResponse: LiveData<SummonerResponse?> get() = _summonerResponse
    private val _summonerResponse = MutableLiveData<SummonerResponse?>()

    val summonerNotFound: LiveData<Boolean> get() = _summonerNotFound
    private val _summonerNotFound = MutableLiveData<Boolean>()

    val setFocus: LiveData<Unit> get() = _setFocus
    private val _setFocus = MutableLiveData<Unit>()

    val slideUpAnimation: LiveData<Pair<Animation, Animation>> get() = _slideUpAnimation
    private val _slideUpAnimation = MutableLiveData<Pair<Animation, Animation>>()
    val slideDownAnimation: LiveData<Pair<Animation, Animation>> get() = _slideDownAnimation
    private val _slideDownAnimation = MutableLiveData<Pair<Animation, Animation>>()

    fun fetchSummonerByName(summonerName: String, actionId: Int) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            defaultLaunch(exceptionHandler = ({
                if (it is HttpException && (it.code() == HttpStatus.HTTP_NOT_FOUND
                            || it.code() == 403)
                ) {
                    _summonerNotFound.postValue(true)
                }
            })) {
                val response = summonerRepository.fetchSummonerByName(summonerName)
                if (response != null)
                    _summonerResponse.postValue(response)
                else
                    _summonerNotFound.postValue(true)
            }
        }
    }

    fun setFocus(actionId: Int) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            _setFocus.postValue(Unit)
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

    fun resetSummonerValue() = _summonerResponse.postValue(null)


}