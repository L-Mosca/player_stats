package br.com.lol_app.screen.summonertier

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse
import br.com.lol_app.utils.getWinRate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SummonerTierViewModel @Inject constructor() :
    BaseViewModel() {

    private val _summonerTier = MutableLiveData<SummonerMainTierResponse>()
    val summonerTier: LiveData<SummonerMainTierResponse> get() = _summonerTier

    private val _rankData = MutableLiveData<Triple<String, String, Int>>()
    val rankData: LiveData<Triple<String, String, Int>> get() = _rankData

    private val _winRate = MutableLiveData<Triple<Int, Int, String>>()
    val winRate: LiveData<Triple<Int, Int, String>> get() = _winRate

    private val _noTierData = MutableLiveData<Unit>()
    val noTierData: LiveData<Unit> get() = _noTierData

    fun setupInitialData(summonerMainTierResponse: SummonerMainTierResponse?) {

        if (summonerMainTierResponse == null) {
            _noTierData.postValue(Unit)
            return
        }

        summonerMainTierResponse.let {
            _summonerTier.postValue(it)

            setupTierData(it.tier, it.rank, it.leaguePoints)
            setupWinRateData(it.wins, it.losses)
        }
    }

    private fun setupTierData(tier: String?, rank: String?, points: Int?) {
        if (!tier.isNullOrEmpty() && !rank.isNullOrEmpty() && points != null) {
            _rankData.postValue(Triple(tier, rank, points))
        }
    }

    private fun setupWinRateData(wins: Int?, loses: Int?) {
        if (wins != null && wins >= 0 && loses != null && loses >= 0) {
            val winRate = getWinRate(wins, loses)
            _winRate.postValue(Triple(wins, loses, winRate))
        }
    }
}