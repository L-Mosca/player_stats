package br.com.lol_app.domain.model.summoner

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummonerBaseData(
    val name: String,
    val level: Int,
    val icon: Int
) : Parcelable
