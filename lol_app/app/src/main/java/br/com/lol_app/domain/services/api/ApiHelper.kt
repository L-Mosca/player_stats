package br.com.lol_app.domain.services.api

import javax.inject.Inject

class ApiHelper @Inject constructor(private val api: DbApi) : ApiHelperContract {

    override suspend fun fetchSummonerByName(summonerName: String) =
        api.fetchSummonerByName(summonerName)

    override suspend fun fetchFreeChampionsRotation() = api.fetchFreeChampionsRotation()
}