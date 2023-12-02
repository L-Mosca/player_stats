package br.com.lol_app.domain.repositories.match

import br.com.lol_app.domain.model.matches.MatchDetailResponse
import br.com.lol_app.domain.services.api.ApiHelperContract
import javax.inject.Inject

class MatchRepository @Inject constructor(private val apiHelper: ApiHelperContract) :
    MatchRepositoryContract {

    override suspend fun fetchMatchHistoricData(matchId: String): MatchDetailResponse? =
        apiHelper.fetchMatchData(matchId)

    override suspend fun fetchLastMatchesIDs(summonerPUUid: String): List<String?>? =
        apiHelper.fetchHistoricList(summonerPUUid)
}