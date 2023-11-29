package br.com.lol_app.utils

import br.com.lol_app.R
import br.com.lol_app.domain.model.summoner.SummonerEntity
import br.com.lol_app.domain.model.summoner.SummonerResponse
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse
import java.util.Calendar

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

/**
 * Get tier image by api name. Api return a tier name in english and this function convert this in image on drawable resource
 * @return Return a drawable resource id
 */
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

/**
 * Get tier name by api name. Api return in english and this function convert to string resource with internationalization native Android feature
 * @return Return a string resource from api tier string
 */
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

/**
 * Get summoner win rate by wins and loses games number and return a string data in format:
 * - 34.11%;
 * - 56.22%;
 * - 65.00%;
 *
 * totalGames = wins + loses
 * Expression: ((wins / totalGames) * 100)
 *
 * @param wins: Wins number
 * @param loses: Loses number
 *
 * @return Return a string in format: 65.12%
 */
fun getWinRate(wins: Int?, loses: Int?): String {
    val win = wins ?: 0
    val lose = loses ?: 0
    val total = win + lose
    return "%.2f".format((win.toDouble() / total.toDouble()) * 100)
}

/**
 * This extension function convert a SummonerResponse to SummonerEntity
 * @return Return a SummonerEntity type
 * @see SummonerResponse
 * @see SummonerEntity
 */
fun SummonerResponse.toSummonerEntity(): SummonerEntity {
    return SummonerEntity(
        id = id,
        accountId = accountId,
        pUUid = pUUid ?: "",
        name = name,
        profileIconId = profileIconId,
        revisionDate = revisionDate,
        summonerLevel = summonerLevel
    )
}

/**
 * This extension function convert a SummonerEntity to SummonerResponse
 * @return Return a SummonerResponse type
 * @see SummonerEntity
 * @see SummonerResponse
 */
fun SummonerEntity.toSummonerResponse(): SummonerResponse {
    return SummonerResponse(
        id = this.id,
        accountId = this.accountId,
        pUUid = this.pUUid,
        name = this.name,
        profileIconId = this.profileIconId,
        revisionDate = this.revisionDate,
        summonerLevel = this.summonerLevel,
        lastSearch = Calendar.getInstance().time.time
    )
}

/**
 * This extension function update SummonerEntity class with new date data.
 * Are used when user select an RecentSummonerAdapter.onItemClicked invoke in FindSummonerFragment
 * @return Return a SummonerEntity instance with new date data
 * @see br.com.lol_app.screen.findsummoner.FindSummonerFragment
 * @see br.com.lol_app.screen.findsummoner.adapter.RecentSummonerAdapter
 */
fun SummonerEntity.updateDate(): SummonerEntity {
    return SummonerEntity(
        id = this.id,
        accountId = this.accountId,
        pUUid = this.pUUid,
        name = this.name,
        profileIconId = this.profileIconId,
        revisionDate = this.revisionDate,
        summonerLevel = this.summonerLevel,
        lastSearch = Calendar.getInstance().time.time
    )
}

/**
 * This extension function update SummonerEntity class with new tier, rank, leaguePoints and lastSearch data
 * @return Return an update instance of SummonerEntity
 * @see SummonerEntity
 */
fun SummonerEntity.updateTier(tierData: SummonerMainTierResponse): SummonerEntity {
    return SummonerEntity(
        id = this.id,
        accountId = this.accountId,
        pUUid = this.pUUid,
        name = this.name,
        profileIconId = this.profileIconId,
        revisionDate = this.revisionDate,
        summonerLevel = this.summonerLevel,
        lastSearch = Calendar.getInstance().time.time,
        tier = tierData.tier,
        rank = tierData.rank,
        leaguePoints = tierData.leaguePoints
    )
}