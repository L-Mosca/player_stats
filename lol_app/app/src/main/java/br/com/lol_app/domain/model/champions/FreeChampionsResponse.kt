package br.com.lol_app.domain.model.champions

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * This class contains list with free champions rotation ID`s (default free week and free week to new summoners)
 * @param championIds List with champions ID`s. Use ChampionsExtension functions to get champions names and descriptions
 * @param championIdsForNewPlayers List with champions ID`s to new players. Use ChampionsExtension functions to get champions names and descriptions
 * @param maxNewPlayerLevel Integer number to set who have access to championIdsForNewPlayers (max summoner level)
 */
@Parcelize
data class FreeChampionsResponse(
    @SerializedName("freeChampionIds")
    val championIds: List<Int>?,
    @SerializedName("freeChampionIdsForNewPlayers")
    val championIdsForNewPlayers: List<Int>?,
    @SerializedName("maxNewPlayerLevel")
    val maxNewPlayerLevel: Int?
) : Parcelable
