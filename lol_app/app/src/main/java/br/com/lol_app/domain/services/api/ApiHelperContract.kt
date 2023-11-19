package br.com.lol_app.domain.services.api

import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.domain.model.champions.FreeChampionsResponse
import br.com.lol_app.domain.model.summoner.SummonerResponse

interface ApiHelperContract {

    suspend fun fetchSummonerByName(summonerName: String): SummonerResponse?

    suspend fun fetchFreeChampionsRotation(): FreeChampionsResponse?

    suspend fun fetchChampionsMasteries(summonerId: String): List<ChampionBaseData>?
}