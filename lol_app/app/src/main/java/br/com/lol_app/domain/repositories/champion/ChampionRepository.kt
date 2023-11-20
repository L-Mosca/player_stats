package br.com.lol_app.domain.repositories.champion

import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.domain.services.api.ApiHelperContract
import javax.inject.Inject

class ChampionRepository @Inject constructor(private val apiHelper: ApiHelperContract) :
    ChampionRepositoryContract {

    override suspend fun fetchFreeChampionsRotation() = apiHelper.fetchFreeChampionsRotation()

    override suspend fun fetchChampionsMasteries(summonerId: String) = apiHelper.fetchChampionsMasteries(summonerId)
}