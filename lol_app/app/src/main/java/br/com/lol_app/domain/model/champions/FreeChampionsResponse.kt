package br.com.lol_app.domain.model.champions

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FreeChampionsResponse(
    @SerializedName("freeChampionIds")
    val championIds: List<Int>?,
    @SerializedName("freeChampionIdsForNewPlayers")
    val championIdsForNewPlayers: List<Int>?,
    @SerializedName("maxNewPlayerLevel")
    val maxNewPlayerLevel: Int?
) : Parcelable
