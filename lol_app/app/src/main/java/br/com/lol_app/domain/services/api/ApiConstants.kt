package br.com.lol_app.domain.services.api

class ApiConstants {

    companion object {
        // --- Summoner URL ---
        const val SUMMONER_BY_NAME = "/lol/summoner/v4/summoners/by-name/{name}"

        // --- Champions URL ---
        const val FREE_CHAMPIONS_ROTATION = "/lol/platform/v3/champion-rotations"
    }
}