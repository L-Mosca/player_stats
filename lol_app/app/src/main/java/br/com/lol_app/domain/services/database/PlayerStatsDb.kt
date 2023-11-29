package br.com.lol_app.domain.services.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.lol_app.BuildConfig
import br.com.lol_app.domain.model.summoner.SummonerEntity

/**
 * This class is app local database and extends RoomDatabase.
 * Actually has one entity (_SummonerEntity_)
 * Your data access object is _PlayerStatsDAO_ and _DataConverter_ can used to convert Long to Date and Date to Long
 *
 * This instance is created in application class with *_getInstance_* function and this ensures an unique database instance on all app lifecycle
 *
 * @see PlayerStatsDAO
 * @see RoomDatabase
 * @see DataConverter
 */
@Database(entities = [SummonerEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class PlayerStatsDb : RoomDatabase() {

    abstract fun playerStatsDao(): PlayerStatsDAO

    companion object {
        private var INSTANCE: PlayerStatsDb? = null

        fun getInstance(context: Context): PlayerStatsDb {
            return if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PlayerStatsDb::class.java,
                        "${BuildConfig.APP_NAME}${BuildConfig.FLAVOR}_Database".replace("/", "_")
                    ).fallbackToDestructiveMigration().build()
                }
                INSTANCE as PlayerStatsDb
            } else {
                INSTANCE as PlayerStatsDb
            }
        }
    }
}