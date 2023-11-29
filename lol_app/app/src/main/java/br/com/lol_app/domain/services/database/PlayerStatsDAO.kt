package br.com.lol_app.domain.services.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.lol_app.domain.model.summoner.SummonerEntity

/**
 * This interface are database (_PlayerStatsDb_) data access object.
 * Use to access local database data.
 */
@Dao
interface PlayerStatsDAO {

    /**
     * Insert a summoner data (SummonerEntity instance) in database with replace conflict strategy
     * @param summonerData Summoner data (SummonerEntity instance)
     * @see SummonerEntity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSummoner(summonerData: SummonerEntity)

    /**
     * Fetch all "summoner_entity" table lines
     * @return Return a List of SummonerEntity class
     */
    @Query("SELECT * FROM summoner_entity")
    suspend fun getAllSummoner(): List<SummonerEntity>

    /**
     * Fetch a SummonerEntity by id (summonerId) on "summoner_entity" table
     * @param summonerId This string are summoner id (SummonerEntity.id)
     * @return Return a SummonerEntity class
     * @see SummonerEntity
     * @see SummonerEntity.id
     */
    @Query("SELECT * FROM summoner_entity WHERE id = :summonerId")
    suspend fun getSummonerById(summonerId: String): SummonerEntity

    /**
     * Update one "summoner_entity" table data with replace conflict strategy
     * @param summonerData SummonerEntity instance
     * @see SummonerEntity
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSummonerData(summonerData: SummonerEntity)

    /**
     * Delete one "summoner_entity" table data from SummonerEntity instance (compare primary key SummonerEntity.pUUid)
     * @param summonerData SummonerEntity instance
     * @see SummonerEntity
     */
    @Delete
    suspend fun deleteSummoner(summonerData: SummonerEntity)
}