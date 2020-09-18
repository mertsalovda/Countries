package ru.mertsalovda.countries.models.data

import com.google.gson.annotations.SerializedName

/**
 * Модель описывает валюту
 *
 * @property code - сокращённое наименование валюты
 * @property name - название валюты
 * @property symbol - символьное обозначение валюты
 */
data class Currencies(
	@SerializedName("code")
	val code: String,
	@SerializedName("name")
	val name: String,
	@SerializedName("symbol")
	val symbol: String
) {

    override fun toString(): String {
        return """
$name ( $code )
Символьное обозначение: $symbol

""".trimIndent()
    }
}
