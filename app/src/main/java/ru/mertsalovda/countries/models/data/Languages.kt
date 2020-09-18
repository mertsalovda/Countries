package ru.mertsalovda.countries.models.data

import com.google.gson.annotations.SerializedName

/**
 * Модель описывает язык
 *
 * @property name - общепринятое название языка
 * @property nativeName - название языка с использование алвавита данного языка
 * @property iso639_1 - сокращённое наименование iso639_1
 * @property iso639_2 - сокращённое наименование iso639_2
 */
data class Languages(
	@SerializedName("name") val name: String,
	@SerializedName("nativeName") val nativeName: String,
	@SerializedName("iso639_1") val iso639_1: String,
	@SerializedName("iso639_2") val iso639_2: String
) {
    override fun toString(): String {
        return """
$name ( $nativeName )
Краткое обозначение: $iso639_1; $iso639_2

""".trimIndent()
    }
}
