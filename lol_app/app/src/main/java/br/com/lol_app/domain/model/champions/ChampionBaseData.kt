package br.com.lol_app.domain.model.champions

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Calendar
import java.util.Date

/**
 * This class contains a mastery champion data.
 * @param championId Champion ID. Use ChampionExtensions to get name and description
 * @param championPoints Champion mastery points
 * @param championLevel Champion mastery level
 * @param chestGranted Return true if summoner granted a chest with this champion
 * @param tokensEarned Champion mastery fragment earned (mastery 6 or mastery 7 fragments)
 */
@Parcelize
data class ChampionBaseData(
    @SerializedName("puuid")
    val puuId: String?,
    @SerializedName("championId")
    val championId: Int?,
    @SerializedName("championLevel")
    val championLevel: Int?,
    @SerializedName("championPoints")
    val championPoints: Int?,
    @SerializedName("lastPlayTime")
    val lastPlayTime: Long?,
    @SerializedName("championPointsSinceLastLevel")
    val championPointsSinceLastLevel: Int?,
    @SerializedName("championPointsUntilNextLevel")
    val championPointsUntilNextLevel: Int?,
    @SerializedName("chestGranted")
    val chestGranted: Boolean?,
    @SerializedName("tokensEarned")
    val tokensEarned: Int?,
    @SerializedName("summonerId")
    val summonerId: String?
) : Parcelable {

    fun getDate() = Date(lastPlayTime ?: Calendar.getInstance().timeInMillis)
}