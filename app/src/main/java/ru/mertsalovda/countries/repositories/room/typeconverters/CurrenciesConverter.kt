package ru.mertsalovda.countries.repositories.room.typeconverters

import androidx.room.TypeConverter
import ru.mertsalovda.countries.models.data.Currencies

/**
 * [TypeConverter] для списка действующих валют
 *
 */
class CurrenciesConverter {
    /**
     * Преобразовать коллекцию валют в строку
     *
     * @param currencies коллекция валют [Currencies]
     * @return строковое представление коллекции [Currencies]
     */
    @TypeConverter
    fun listToString(currencies: List<Currencies>): String {
        val stringBuilder = StringBuilder()
        for (curr in currencies) {
            stringBuilder.append(curr.code)
            stringBuilder.append(",")
            stringBuilder.append(curr.name)
            stringBuilder.append(",")
            stringBuilder.append(curr.symbol)
            stringBuilder.append(";")
        }
        stringBuilder.also {
            it.replace(it.length - 1, it.length, "")
        }
        return stringBuilder.toString()
    }

    /**
     * Преобразовать строковое представление валют в коллекцию валют
     *
     * @param currencies строковое представление валют
     * @return коллекцию валют [Currencies]
     */
    @TypeConverter
    fun stringToList(currencies: String): List<Currencies> {
        return currencies.split(";")
            .map {
                val s = it.split(",")
                Currencies(
                    code = s[0],
                    name = s[1],
                    symbol = s[2]
                )
            }
    }
}