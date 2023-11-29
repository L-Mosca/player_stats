package br.com.lol_app.domain.model.summoner

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Calendar

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
    @SerializedName("lastSearch")
    var lastSearch: Long? = Calendar.getInstance().time.time
) : Parcelable
