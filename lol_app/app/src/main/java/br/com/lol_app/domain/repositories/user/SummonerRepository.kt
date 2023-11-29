package br.com.lol_app.domain.repositories.user

import android.app.Application
import br.com.lol_app.App
import br.com.lol_app.domain.model.summoner.SummonerEntity
import br.com.lol_app.domain.services.api.ApiHelperContract
import javax.inject.Inject

class SummonerRepository @Inject constructor(
    private val apiHelper: ApiHelperContract,
    private val application: Application
) : SummonerRepositoryContract {

    override suspend fun fetchSummonerByName(summonerName: String) =
        apiHelper.fetchSummonerByName(summonerName)

    override suspend fun fetchSummonerMainTier(summonerId: String) =
        apiHelper.fetchSummonerMainTier(summonerId)

    override suspend fun fetchRecentSummoners(): List<SummonerEntity> {
        val app = application as App
        val dao = app.db.playerStatsDao()
        val list = dao.getAllSummoner()
        return list.sortedByDescending { it.lastSearch }
    }

    override suspend fun insertSummoner(summonerData: SummonerEntity) {
        val app = application as App
        val dao = app.db.playerStatsDao()
        dao.insertSummoner(summonerData)
    }

    override suspend fun updateSummonerData(summonerData: SummonerEntity) {
        val app = application as App
        val dao = app.db.playerStatsDao()
        dao.updateSummonerData(summonerData)
    }

    override suspend fun deleteSummoner(summonerData: SummonerEntity) {
        val app = application as App
        val dao = app.db.playerStatsDao()
        dao.deleteSummoner(summonerData)
    }

    override suspend fun fetchRecentSummonerById(summonerId: String): SummonerEntity {
        val app = application as App
        val dao = app.db.playerStatsDao()
        return dao.getSummonerById(summonerId)
    }
}