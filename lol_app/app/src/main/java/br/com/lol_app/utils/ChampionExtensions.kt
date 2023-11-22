package br.com.lol_app.utils

import android.content.Context
import br.com.lol_app.R

/***
 * This extension function receive a champion ID (integer number) and convert to this name (String)
 * @receiver Receive a champion ID (integer)
 * @return Return a champion name
 */
fun Int?.getChampionNameById(): Int {
    return when (this) {
        1 -> R.string.annie
        2 -> R.string.olaf
        3 -> R.string.galio
        4 -> R.string.twisted_fate
        5 -> R.string.xin_zhao
        6 -> R.string.urgot
        7 -> R.string.leblanc
        8 -> R.string.vladimir
        9 -> R.string.fiddlesticks
        10 -> R.string.kayle
        11 -> R.string.master_yi
        12 -> R.string.alistar
        13 -> R.string.ryze
        14 -> R.string.sion
        15 -> R.string.sivir
        16 -> R.string.soraka
        17 -> R.string.teemo
        18 -> R.string.tristana
        19 -> R.string.warwick
        20 -> R.string.nunu
        21 -> R.string.miss_fortune
        22 -> R.string.ashe
        23 -> R.string.tryndamere
        24 -> R.string.jax
        25 -> R.string.morgana
        26 -> R.string.zilean
        27 -> R.string.singed
        28 -> R.string.evelynn
        29 -> R.string.twitch
        30 -> R.string.karthus
        31 -> R.string.cho_gath
        32 -> R.string.amumu
        33 -> R.string.rammus
        34 -> R.string.anivia
        35 -> R.string.shaco
        36 -> R.string.dr_mundo
        37 -> R.string.sona
        38 -> R.string.kassadin
        39 -> R.string.irelia
        40 -> R.string.janna
        41 -> R.string.gangplank
        42 -> R.string.corki
        43 -> R.string.karma
        44 -> R.string.taric
        45 -> R.string.veigar
        48 -> R.string.trundle
        50 -> R.string.swain
        51 -> R.string.caitlyn
        53 -> R.string.blitzcrank
        54 -> R.string.malphite
        55 -> R.string.katarina
        56 -> R.string.nocturne
        57 -> R.string.maokai
        58 -> R.string.renekton
        59 -> R.string.jarvan_iv
        60 -> R.string.elise
        61 -> R.string.orianna
        62 -> R.string.wukong
        63 -> R.string.brand
        64 -> R.string.lee_sin
        67 -> R.string.vayne
        68 -> R.string.rumble
        69 -> R.string.cassiopeia
        72 -> R.string.skarner
        74 -> R.string.heimerdinger
        75 -> R.string.nasus
        76 -> R.string.nidalee
        77 -> R.string.udyr
        78 -> R.string.poppy
        79 -> R.string.gragas
        80 -> R.string.pantheon
        81 -> R.string.ezreal
        82 -> R.string.mordekaiser
        83 -> R.string.yorick
        84 -> R.string.akali
        85 -> R.string.kennen
        86 -> R.string.garen
        89 -> R.string.leona
        90 -> R.string.malzahar
        91 -> R.string.talon
        92 -> R.string.riven
        96 -> R.string.kog_maw
        98 -> R.string.shen
        99 -> R.string.lux
        101 -> R.string.xerath
        102 -> R.string.shyvana
        103 -> R.string.ahri
        104 -> R.string.graves
        105 -> R.string.fizz
        106 -> R.string.volibear
        107 -> R.string.rengar
        110 -> R.string.varus
        111 -> R.string.nautilus
        112 -> R.string.viktor
        113 -> R.string.sejuani
        114 -> R.string.fiora
        115 -> R.string.ziggs
        117 -> R.string.lulu
        119 -> R.string.draven
        120 -> R.string.hecarim
        121 -> R.string.kha_zix
        122 -> R.string.darius
        126 -> R.string.jayce
        127 -> R.string.lissandra
        131 -> R.string.diana
        133 -> R.string.quinn
        134 -> R.string.syndra
        136 -> R.string.aurelion_sol
        141 -> R.string.kayn
        142 -> R.string.zoe
        143 -> R.string.zyra
        145 -> R.string.kai_sa
        147 -> R.string.seraphine
        150 -> R.string.gnar
        154 -> R.string.zac
        157 -> R.string.yasuo
        161 -> R.string.vel_koz
        163 -> R.string.taliyah
        164 -> R.string.camile
        166 -> R.string.akshan
        200 -> R.string.bel_veth
        201 -> R.string.braum
        202 -> R.string.jhin
        203 -> R.string.kindred
        221 -> R.string.zeri
        222 -> R.string.jinx
        223 -> R.string.tahm_kench
        233 -> R.string.briar
        234 -> R.string.viego
        235 -> R.string.senna
        236 -> R.string.lucian
        238 -> R.string.zed
        240 -> R.string.kled
        245 -> R.string.ekko
        246 -> R.string.qiyana
        254 -> R.string.vi
        266 -> R.string.aatrox
        267 -> R.string.nami
        268 -> R.string.azir
        350 -> R.string.yuumi
        360 -> R.string.samira
        412 -> R.string.thresh
        420 -> R.string.illaoi
        421 -> R.string.rek_sai
        427 -> R.string.ivern
        429 -> R.string.kalista
        432 -> R.string.bard
        497 -> R.string.rakan
        498 -> R.string.xayah
        516 -> R.string.ornn
        517 -> R.string.sylas
        518 -> R.string.neeko
        523 -> R.string.aphelios
        526 -> R.string.rell
        555 -> R.string.pyke
        711 -> R.string.vex
        777 -> R.string.yone
        875 -> R.string.sett
        876 -> R.string.lillia
        887 -> R.string.gwen
        888 -> R.string.renata_glasc
        895 -> R.string.nilah
        897 -> R.string.k_sant
        902 -> R.string.milio
        950 -> R.string.naafiri
        else -> R.string.invalid_champion_id
    }
}

/***
 * Receive champions id list, convert in name and return champions name list
 * @receiver Receive a list with champions ID
 * @return Return a list with champions name
 */
fun List<Int>?.getChampionNameById(context: Context): List<String> {
    val championList = mutableListOf<String>()

    this?.forEach { championId ->
        championList.add(context.getString(championId.getChampionNameById()))
    }

    // Sort list alphabetically
    championList.sortBy { championName -> championName }

    return championList
}


fun Int?.getChampionDescriptionById(): Int {
    return when (this) {
        1 -> R.string.annie_description
        2 -> R.string.olaf_description
        3 -> R.string.galio_description
        4 -> R.string.twisted_fate_description
        5 -> R.string.xin_zhao_description
        6 -> R.string.urgot_description
        7 -> R.string.leblanc_description
        8 -> R.string.vladimir_description
        9 -> R.string.fiddlesticks_description
        10 -> R.string.kayle_description
        11 -> R.string.master_yi_description
        12 -> R.string.alistar_description
        13 -> R.string.ryze_description
        14 -> R.string.sion_description
        15 -> R.string.sivir_description
        16 -> R.string.soraka_description
        17 -> R.string.teemo_description
        18 -> R.string.tristana_description
        19 -> R.string.warwick_description
        20 -> R.string.nunu_description
        21 -> R.string.miss_fortune_description
        22 -> R.string.ashe_description
        23 -> R.string.tryndamere_description
        24 -> R.string.jax_description
        25 -> R.string.morgana_description
        26 -> R.string.zilean_description
        27 -> R.string.singed_description
        28 -> R.string.evelynn_description
        29 -> R.string.twitch_description
        30 -> R.string.karthus_description
        31 -> R.string.cho_gath_description
        32 -> R.string.amumu_description
        33 -> R.string.rammus_description
        34 -> R.string.anivia_description
        35 -> R.string.shaco_description
        36 -> R.string.dr_mundo_description
        37 -> R.string.sona_description
        38 -> R.string.kassadin_description
        39 -> R.string.irelia_description
        40 -> R.string.janna_description
        41 -> R.string.gangplank_description
        42 -> R.string.corki_description
        43 -> R.string.karma_description
        44 -> R.string.taric_description
        45 -> R.string.veigar_description
        48 -> R.string.trundle_description
        50 -> R.string.swain_description
        51 -> R.string.caitlyn_description
        53 -> R.string.blitzcrank_description
        54 -> R.string.malphite_description
        55 -> R.string.katarina_description
        56 -> R.string.nocturne_description
        57 -> R.string.maokai_description
        58 -> R.string.renekton_description
        59 -> R.string.jarvan_iv_description
        60 -> R.string.elise_description
        61 -> R.string.orianna_description
        62 -> R.string.wukong_description
        63 -> R.string.brand_description
        64 -> R.string.lee_sin_description
        67 -> R.string.vayne_description
        68 -> R.string.rumble_description
        69 -> R.string.cassiopeia_description
        72 -> R.string.skarner_description
        74 -> R.string.heimerdinger_description
        75 -> R.string.nasus_description
        76 -> R.string.nidalee_description
        77 -> R.string.udyr_description
        78 -> R.string.poppy_description
        79 -> R.string.gragas_description
        80 -> R.string.pantheon_description
        81 -> R.string.ezreal_description
        82 -> R.string.mordekaiser_description
        83 -> R.string.yorick_description
        84 -> R.string.akali_description
        85 -> R.string.kennen_description
        86 -> R.string.garen_description
        89 -> R.string.leona_description
        90 -> R.string.malzahar_description
        91 -> R.string.talon_description
        92 -> R.string.riven_description
        96 -> R.string.kog_maw_description
        98 -> R.string.shen_description
        99 -> R.string.lux_description
        101 -> R.string.xerath_description
        102 -> R.string.shyvana_description
        103 -> R.string.ahri_description
        104 -> R.string.graves_description
        105 -> R.string.fizz_description
        106 -> R.string.volibear_description
        107 -> R.string.rengar_description
        110 -> R.string.varus_description
        111 -> R.string.nautilus_description
        112 -> R.string.viktor_description
        113 -> R.string.sejuani_description
        114 -> R.string.fiora_description
        115 -> R.string.ziggs_description
        117 -> R.string.lulu_description
        119 -> R.string.draven_description
        120 -> R.string.hecarim_description
        121 -> R.string.kha_zix_description
        122 -> R.string.darius_description
        126 -> R.string.jayce_description
        127 -> R.string.lissandra_description
        131 -> R.string.diana_description
        133 -> R.string.quinn_description
        134 -> R.string.syndra_description
        136 -> R.string.aurelion_sol_description
        141 -> R.string.kayn_description
        142 -> R.string.zoe_description
        143 -> R.string.zyra_description
        145 -> R.string.kai_sa_description
        147 -> R.string.seraphine_description
        150 -> R.string.gnar_description
        154 -> R.string.zac_description
        157 -> R.string.yasuo_description
        161 -> R.string.vel_koz_description
        163 -> R.string.taliyah_description
        164 -> R.string.camile_description
        166 -> R.string.akshan_description
        200 -> R.string.bel_veth_description
        201 -> R.string.braum_description
        202 -> R.string.jhin_description
        203 -> R.string.kindred_description
        221 -> R.string.zeri_description
        222 -> R.string.jinx_description
        223 -> R.string.tahm_kench_description
        233 -> R.string.briar_description
        234 -> R.string.viego_description
        235 -> R.string.senna_description
        236 -> R.string.lucian_description
        238 -> R.string.zed_description
        240 -> R.string.kled_description
        245 -> R.string.ekko_description
        246 -> R.string.qiyana_description
        254 -> R.string.vi_description
        266 -> R.string.aatrox_description
        267 -> R.string.nami_description
        268 -> R.string.azir_description
        350 -> R.string.yuumi_description
        360 -> R.string.samira_description
        412 -> R.string.thresh_description
        420 -> R.string.illaoi_description
        421 -> R.string.rek_sai_description
        427 -> R.string.ivern_description
        429 -> R.string.kalista_description
        432 -> R.string.bard_description
        497 -> R.string.rakan_description
        498 -> R.string.xayah_description
        516 -> R.string.ornn_description
        517 -> R.string.sylas_description
        518 -> R.string.neeko_description
        523 -> R.string.aphelios_description
        526 -> R.string.rell_description
        555 -> R.string.pyke_description
        711 -> R.string.vex_description
        777 -> R.string.yone_description
        875 -> R.string.sett_description
        876 -> R.string.lillia_description
        887 -> R.string.gwen_description
        888 -> R.string.renata_glasc_description
        895 -> R.string.nilah_description
        897 -> R.string.k_sant_description
        902 -> R.string.milio_description
        950 -> R.string.naafiri_description
        else -> R.string.invalid_champion_id_description
    }
}

fun List<Int>?.getChampionDescriptionById(context: Context): List<String> {
    val descriptionList = mutableListOf<String>()

    this?.forEach { championId ->
        descriptionList.add(context.getString(championId.getChampionDescriptionById()))
    }

    return descriptionList

}

fun Int.getChampionMasteryLevel(): Int {
    return when (this) {
        4 -> R.drawable.img_mastery_4
        5 -> R.drawable.img_mastery_5
        6 -> R.drawable.img_mastery_6
        7 -> R.drawable.img_mastery_7
        else -> 0
    }
}