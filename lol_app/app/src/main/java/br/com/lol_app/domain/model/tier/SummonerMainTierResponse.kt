package br.com.lol_app.domain.model.tier

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/***
 * This class represent summoner main tier. We use this to get basic summoner tier data to show in SummonerDetailFragment
 * in tier fragment container view.
 */
@Parcelize
data class SummonerMainTierResponse(
    @SerializedName("leagueId")
    val leagueId: String?,
    @SerializedName("summonerId")
    val summonerId: String?,
    @SerializedName("summonerName")
    val summonerName: String?,
    @SerializedName("queueType")
    val queueType: String?,
    @SerializedName("tier")
    val tier: String?,
    @SerializedName("rank")
    val rank: String?,
    @SerializedName("leaguePoints")
    val leaguePoints: Int?,
    @SerializedName("wins")
    val wins: Int?,
    @SerializedName("losses")
    val losses: Int?,
    @SerializedName("hotStreak")
    val hotStreak: Boolean?,
    @SerializedName("veteran")
    val veteran: Boolean?,
    @SerializedName("freshBlood")
    val freshBlood: Boolean?,
    @SerializedName("inactive")
    val inactive: Boolean?,
    @SerializedName("miniSeries")
    val miniSeries: MiniSeries?,
) : Parcelable

@Parcelize
data class MiniSeries(
    @SerializedName("losses")
    val losses: Int?,
    @SerializedName("progress")
    val progress: String?,
    @SerializedName("target")
    val target: Int?,
    @SerializedName("wins")
    val wins: Int?,
) : Parcelable
