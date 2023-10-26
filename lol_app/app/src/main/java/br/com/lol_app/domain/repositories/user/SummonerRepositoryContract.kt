package br.com.lol_app.domain.repositories.user

import br.com.lol_app.domain.model.summoner.SummonerResponse

interface SummonerRepositoryContract {

   suspend fun fetchSummonerByName(summonerName: String) : SummonerResponse?
}