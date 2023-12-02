package br.com.lol_app.domain.repositories.match

import br.com.lol_app.domain.model.matches.MatchDetailResponse

interface MatchRepositoryContract {

    suspend fun fetchMatchHistoricData(matchId: String): MatchDetailResponse?

    suspend fun fetchLastMatchesIDs(summonerPUUid: String): List<String?>?
}