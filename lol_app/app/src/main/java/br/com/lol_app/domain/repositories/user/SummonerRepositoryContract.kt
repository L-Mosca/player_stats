package br.com.lol_app.domain.repositories.user

import br.com.lol_app.domain.model.summoner.SummonerResponse
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse

interface SummonerRepositoryContract {

   suspend fun fetchSummonerByName(summonerName: String) : SummonerResponse?

   suspend fun fetchSummonerMainTier(summonerId: String) : List<SummonerMainTierResponse?>
}