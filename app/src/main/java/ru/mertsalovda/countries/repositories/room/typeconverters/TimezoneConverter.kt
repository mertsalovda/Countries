package ru.mertsalovda.countries.repositories.room.typeconverters

import androidx.room.TypeConverter

/**
 * [TypeConverter] для списка таймзон
 *
 */
class TimezoneConverter {

    /**
     * Преобразовать коллекцию таймзон в одну строку
     *
     * @param timezones коллекция таймзон
     * @return строку таймзон
     */
    @TypeConverter
    fun listToString(timezones: List<String>): String {
        val stringBuilder = StringBuilder()
        for (timezone in timezones) {
            stringBuilder.append(timezone)
            stringBuilder.append(",")
        }
        stringBuilder.also {
            it.replace(it.length - 1, it.length, "")
        }
        return stringBuilder.toString()
    }

    /**
     * Преобразовать строку в коллекцию таймзон
     *
     * @param timezones срока таймзон
     * @return коллекция таймзон
     */
    @TypeConverter
    fun stringToList(timezones: String): List<String> {
        return timezones.split(",")
    }
}