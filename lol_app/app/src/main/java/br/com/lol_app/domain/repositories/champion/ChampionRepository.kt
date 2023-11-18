package br.com.lol_app.domain.repositories.champion

import br.com.lol_app.domain.services.api.ApiHelperContract
import javax.inject.Inject

class ChampionRepository @Inject constructor(private val apiHelper: ApiHelperContract) :
    ChampionRepositoryContract {

    override suspend fun fetchFreeChampionsRotation() = apiHelper.fetchFreeChampionsRotation()
}