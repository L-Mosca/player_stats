package br.com.lol_app.domain.model.summoner

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummonerResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("accountId")
    val accountId: String?,
    @SerializedName("puuid")
    val pUUid: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profileIconId")
    val profileIconId: Int?,
    @SerializedName("revisionDate")
    val revisionDate: Long?,
    @SerializedName("summonerLevel")
    val summonerLevel: Int?,
    ) : Parcelable
