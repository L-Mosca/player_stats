package br.com.lol_app.domain.services.database

import androidx.room.TypeConverter
import java.util.Date

/**
 * Use to convert data of SummonerEntity characteristics
 * @see br.com.lol_app.domain.model.summoner.SummonerEntity.revisionDate
 * @see br.com.lol_app.domain.model.summoner.SummonerEntity.lastSearch
 */
object DataConverter {

    /**
     * Convert Long data ty Date type
     * @param dateLong date in timeMillis type
     * @return Convert Long and return a Date type
     * @see Date
     * @see Date.getTime
     */
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong != null) Date(dateLong) else null
    }

    /**
     * Cpmvert Date to Long type
     * @param date Date data type
     * @return Return a conversion of Date to timeMillis
     * @see Date
     * @see Date.getTime
     */
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}