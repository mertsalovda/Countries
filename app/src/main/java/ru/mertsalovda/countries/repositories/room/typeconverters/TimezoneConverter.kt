package ru.mertsalovda.countries.repositories.room.typeconverters

import androidx.room.TypeConverter

class TimezoneConverter {

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

    @TypeConverter
    fun stringToList(timezones: String): List<String> {
        return timezones.split(",")
    }
}