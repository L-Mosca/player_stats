package br.com.lol_app.domain.services.api

class ApiConstants {

    companion object {
        private const val LOL = "lol"
        private const val SUMMONER = "summoner"
        private const val LEAGUE = "league"
        private const val PLATFORM = "platform"
        private const val MATCH = "match"
        private const val MATCHES = "matches"

        private const val V3 = "v3"
        private const val V4 = "v4"
        private const val V5 = "v5"

        // --- Summoner URL ---
        const val SUMMONER_BY_NAME = "/$LOL/$SUMMONER/$V4/summoners/by-name/{name}"
        const val SUMMONER_MAIN_TIER = "/$LOL/$LEAGUE/$V4/entries/by-summoner/{encryptedSummonerId}"

        // --- Champions URL ---
        const val FREE_CHAMPIONS_ROTATION = "/$LOL/$PLATFORM/$V3/champion-rotations"
        const val CHAMPIONS_MASTERIES_BY_SUMMONER_ID =
            "/$LOL/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}"

        // --- Match Historic URL ---
        const val FETCH_MATCHES_HISTORIC_ID =
            "/$LOL/$MATCH/$V5/$MATCHES/by-puuid/{puuid}/ids?type=normal&start=0&count=20"
        const val FETCH_MATCH_HISTORIC_DATA = "/$LOL/$MATCH/$V5/$MATCHES/{matchId}"
    }
}