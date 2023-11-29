package br.com.lol_app.domain.repositories.user

import br.com.lol_app.domain.model.summoner.SummonerEntity
import br.com.lol_app.domain.model.summoner.SummonerResponse
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse

interface SummonerRepositoryContract {

    suspend fun fetchSummonerByName(summonerName: String): SummonerResponse?

    suspend fun fetchSummonerMainTier(summonerId: String): List<SummonerMainTierResponse?>

    suspend fun fetchRecentSummoners(): List<SummonerEntity>

    /**
     * Insert new summoner data to "summoner_entity" table by SummonerEntity instance (compare primary key SummonerEntity.pUUid) with replace conflict strategy
     * @param summonerData SummonerEntity instance
     * @see SummonerEntity
     * @see br.com.lol_app.domain.services.database.PlayerStatsDAO.insertSummoner
     */
    suspend fun insertSummoner(summonerData: SummonerEntity)

    /**
     * Update summoner data to "summoner_entity" table by SummonerEntity instance (compare primary key SummonerEntity.pUUid) with replace conflict strategy
     * @param summonerData SummonerEntity instance to existent data in "summoner_entity" table
     * @see SummonerEntity.pUUid
     * @see br.com.lol_app.domain.services.database.PlayerStatsDAO.updateSummonerData
     */
    suspend fun updateSummonerData(summonerData: SummonerEntity)

    /**
     * Delete summoner data to "summoner_entity" table by SummonerEntity instance (compare primary key SummonerEntity.pUUid) with replace conflict strategy
     * @param summonerData SummonerEntity instance to existent data in "summoner_entity" table
     * @see SummonerEntity.pUUid
     * @see br.com.lol_app.domain.services.database.PlayerStatsDAO.deleteSummoner
     */
    suspend fun deleteSummoner(summonerData: SummonerEntity)

    /**
     * Fetch summoner in local database ("summoner_entity" table) by summoner id (SummonerEntity.id)
     * @param summonerId SummonerEntity.id
     * @see SummonerEntity.id
     * @see br.com.lol_app.domain.services.database.PlayerStatsDAO.getSummonerById
     * @return Return SummonerEntity instance from "summoner_entity"
     */
    suspend fun fetchRecentSummonerById(summonerId: String): SummonerEntity
}