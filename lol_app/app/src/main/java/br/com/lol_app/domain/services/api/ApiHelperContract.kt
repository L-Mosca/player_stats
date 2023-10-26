package br.com.lol_app.domain.services.api

import br.com.lol_app.domain.model.summoner.SummonerResponse

interface ApiHelperContract {

    suspend fun fetchSummonerByName(summonerName: String) : SummonerResponse?
}