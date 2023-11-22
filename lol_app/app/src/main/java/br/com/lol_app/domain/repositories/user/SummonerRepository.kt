package br.com.lol_app.domain.repositories.user

import br.com.lol_app.domain.services.api.ApiHelperContract
import javax.inject.Inject

class SummonerRepository @Inject constructor(private val apiHelper: ApiHelperContract) :
    SummonerRepositoryContract {

    override suspend fun fetchSummonerByName(summonerName: String) =
        apiHelper.fetchSummonerByName(summonerName)

    override suspend fun fetchSummonerMainTier(summonerId: String) =
        apiHelper.fetchSummonerMainTier(summonerId)
}