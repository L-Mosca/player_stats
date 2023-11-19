package br.com.lol_app.domain.services.api

import br.com.lol_app.domain.model.champions.ChampionBaseData
import javax.inject.Inject

class ApiHelper @Inject constructor(private val api: DbApi) : ApiHelperContract {

    override suspend fun fetchSummonerByName(summonerName: String) =
        api.fetchSummonerByName(summonerName)

    override suspend fun fetchFreeChampionsRotation() = api.fetchFreeChampionsRotation()

    override suspend fun fetchChampionsMasteries(summonerId: String) = api.fetchChampionsMasteries(summonerId)
}