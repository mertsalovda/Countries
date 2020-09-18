package ru.mertsalovda.countries.repositories.room.typeconverters

import androidx.room.TypeConverter
import ru.mertsalovda.countries.models.data.Languages

/**
 * [TypeConverter] для списка государственных языков
 *
 */
class LanguageConverter {
    /**
     * Преобразовать коллекцию языков в строку
     *
     * @param languages коллекция языков [Languages]
     * @return строковое представление коллекции [Languages]
     */
    @TypeConverter
    fun listToString(languages: List<Languages>): String {
        val stringBuilder = StringBuilder()
        for (lang in languages) {
            stringBuilder.append(lang.name)
            stringBuilder.append(",")
            stringBuilder.append(lang.nativeName)
            stringBuilder.append(",")
            stringBuilder.append(lang.iso639_1)
            stringBuilder.append(",")
            stringBuilder.append(lang.iso639_2)
            stringBuilder.append(";")
        }
        stringBuilder.also {
            it.replace(it.length - 1, it.length, "")
        }
        return stringBuilder.toString()
    }

    /**
     * Преобразовать строковое представление языков в коллекцию языков
     *
     * @param languages строковое представление языков
     * @return коллекцию языков [Languages]
     */
    @TypeConverter
    fun stringToList(languages: String): List<Languages> {
        return languages.split(";")
            .map {
                val s = it.split(",")
                Languages(
                    name = s[0],
                    nativeName = s[1],
                    iso639_1 = s[2],
                    iso639_2 = s[3]
                )
            }
    }
}