package br.com.lol_app.domain.services.api

class ApiConstants {

    companion object {
        // --- Summoner URL ---
        const val SUMMONER_BY_NAME = "/lol/summoner/v4/summoners/by-name/{name}"
        const val SUMMONER_MAIN_TIER = "/lol/league/v4/entries/by-summoner/{encryptedSummonerId}"


        // --- Champions URL ---
        const val FREE_CHAMPIONS_ROTATION = "/lol/platform/v3/champion-rotations"
        const val CHAMPIONS_MASTERIES_BY_SUMMONER_ID = "/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}"
    }
}