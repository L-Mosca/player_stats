package br.com.lol_app.domain.services.api

import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.domain.model.champions.FreeChampionsResponse
import br.com.lol_app.domain.model.matches.MatchDetailResponse
import br.com.lol_app.domain.model.summoner.SummonerResponse
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse

interface ApiHelperContract {

    suspend fun fetchSummonerByName(summonerName: String): SummonerResponse?

    suspend fun fetchFreeChampionsRotation(): FreeChampionsResponse?

    suspend fun fetchChampionsMasteries(summonerId: String): List<ChampionBaseData>?

    suspend fun fetchSummonerMainTier(summonerId: String): List<SummonerMainTierResponse?>

    suspend fun fetchHistoricList(pUUid: String): List<String?>?

    suspend fun fetchMatchData(matchId: String): MatchDetailResponse?
}