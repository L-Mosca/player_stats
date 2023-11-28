package br.com.lol_app.utils

import android.util.Log
import br.com.lol_app.R
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse


const val TIER_UNRANKED = "UNRANKED"
const val TIER_IRON = "IRON"
const val TIER_BRONZE = "BRONZE"
const val TIER_SILVER = "SILVER"
const val TIER_GOLD = "GOLD"
const val TIER_PLATINUM = "PLATINUM"
const val TIER_EMERALD = "EMERALD"
const val TIER_DIAMOND = "DIAMOND"
const val TIER_MASTER = "MASTER"
const val TIER_GRANDMASTER = "GRANDMASTER"
const val TIER_CHALLENGER = "CHALLENGER"

const val QUEUE_RANKED_SOLO_5X5 = "RANKED_SOLO_5x5"
const val QUEUE_RANKED_FLEX_5X5 = "RANKED_FLEX_SR"

fun String?.getTierImageByName(): Int {
    return when (this) {
        TIER_UNRANKED -> R.drawable.img_unranked
        TIER_IRON -> R.drawable.img_tier_iron
        TIER_BRONZE -> R.drawable.img_tier_bronze
        TIER_SILVER -> R.drawable.img_tier_silver
        TIER_GOLD -> R.drawable.img_tier_gold
        TIER_PLATINUM -> R.drawable.img_tier_platinum
        TIER_EMERALD -> R.drawable.img_tier_emerald
        TIER_DIAMOND -> R.drawable.img_tier_diamond
        TIER_MASTER -> R.drawable.img_tier_master
        TIER_GRANDMASTER -> R.drawable.img_tier_gradmaster
        TIER_CHALLENGER -> R.drawable.img_tier_challenger
        else -> R.drawable.img_unranked
    }
}

fun String?.getTierByName(): Int {
    return when (this) {
        TIER_UNRANKED -> R.string.tier_unranked
        TIER_IRON -> R.string.tier_iron
        TIER_BRONZE -> R.string.tier_bronze
        TIER_SILVER -> R.string.tier_silver
        TIER_GOLD -> R.string.tier_gold
        TIER_PLATINUM -> R.string.tier_platinum
        TIER_EMERALD -> R.string.tier_emerald
        TIER_DIAMOND -> R.string.tier_diamond
        TIER_MASTER -> R.string.tier_master
        TIER_GRANDMASTER -> R.string.tier_grandmaster
        TIER_CHALLENGER -> R.string.tier_challenger
        else -> R.string.tier_unranked
    }
}

fun getWinRate(wins: Int?, loses: Int?): String {
    val win = wins ?: 0
    val lose = loses ?: 0
    val total = win + lose
    return "%.2f".format((win.toDouble() / total.toDouble()) * 100)
}