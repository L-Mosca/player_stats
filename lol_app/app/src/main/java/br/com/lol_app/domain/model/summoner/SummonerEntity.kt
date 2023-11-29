package br.com.lol_app.domain.model.summoner

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

/**
 *
 * This class is based in *_SummonerResponse_* and has many tier and rank data (tier, rank and league points) and use in local database
 * @see br.com.lol_app.domain.services.database.PlayerStatsDb
 * @see br.com.lol_app.domain.services.database.PlayerStatsDAO
 * @see SummonerResponse
 *
 * @param id: Summoner ID
 * @param accountId: Account ID
 * @param pUUid: Player Universally Unique Identifier
 * @param name: Summoner name
 * @param profileIconId: Summoner icon ID
 * @param revisionDate: Summoner last revision date
 * @param summonerLevel: Summoner level
 * @param lastSearch: Summoner last search
 * @param tier: Summoner tier (silver, gold, platinum...)
 * @param rank: Summoner rank (I, II, III, IV)
 * @param leaguePoints: Summoner league points
 */
@Entity(tableName = "summoner_entity")
data class SummonerEntity(
    @ColumnInfo(name = "id") val id: String? = "",
    @ColumnInfo(name = "account_id") val accountId: String? = "",
    @PrimaryKey(autoGenerate = false) val pUUid: String = "",
    @ColumnInfo(name = "name") val name: String? = "",
    @ColumnInfo(name = "profile_icon_id") val profileIconId: Int? = -10,
    @ColumnInfo(name = "revision_date") val revisionDate: Long? = Calendar.getInstance().time.time,
    @ColumnInfo(name = "summoner_level") val summonerLevel: Int? = -10,
    @ColumnInfo(name = "last_search") var lastSearch: Long? = Calendar.getInstance().time.time,
    @ColumnInfo(name = "tier") val tier: String? = "",
    @ColumnInfo(name = "rank") val rank: String? = "",
    @ColumnInfo(name = "league_points") val leaguePoints: Int? = 0
)
