package br.com.lol_app

import android.app.Application
import br.com.lol_app.domain.services.database.PlayerStatsDb
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    lateinit var db: PlayerStatsDb

    override fun onCreate() {
        super.onCreate()
        db = PlayerStatsDb.getInstance(this)
    }
}