package br.com.lol_app.domain.services.api

import br.com.lol_app.domain.model.matches.MatchDetailResponse
import br.com.lol_app.domain.services.api.matchclient.MatchApi
import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val summonerApi: DbApi,
    private val matchApi: MatchApi
) : ApiHelperContract {

    override suspend fun fetchSummonerByName(summonerName: String) =
        summonerApi.fetchSummonerByName(summonerName)

    override suspend fun fetchFreeChampionsRotation() = summonerApi.fetchFreeChampionsRotation()

    override suspend fun fetchChampionsMasteries(summonerId: String) =
        summonerApi.fetchChampionsMasteries(summonerId)

    override suspend fun fetchSummonerMainTier(summonerId: String) =
        summonerApi.fetchSummonerMainTier(summonerId)

    override suspend fun fetchHistoricList(pUUid: String): List<String?>? {
        return matchApi.fetchHistoricList(pUUid)
    }

    override suspend fun fetchMatchData(matchId: String): MatchDetailResponse? {
        return matchApi.fetchMatchHistoricData(matchId)
    }
}