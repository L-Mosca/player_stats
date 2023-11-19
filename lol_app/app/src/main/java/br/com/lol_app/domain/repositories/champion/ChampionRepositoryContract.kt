package br.com.lol_app.domain.repositories.champion

import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.domain.model.champions.FreeChampionsResponse

interface ChampionRepositoryContract {

    suspend fun fetchFreeChampionsRotation() : FreeChampionsResponse?

    suspend fun fetchChampionsMasteries(summonerId: String) : List<ChampionBaseData>?
}