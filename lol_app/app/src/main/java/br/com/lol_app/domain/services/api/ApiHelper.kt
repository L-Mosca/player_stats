package br.com.lol_app.domain.services.api

import br.com.lol_app.BuildConfig
import br.com.lol_app.domain.model.summoner.SummonerResponse
import javax.inject.Inject

class ApiHelper @Inject constructor(private val api: DbApi) : ApiHelperContract {

    override suspend fun fetchSummonerByName(summonerName: String): SummonerResponse? {
        return api.fetchSummonerByName(summonerName)
    }
}