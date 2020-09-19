package ru.mertsalovda.countries.repositories.room.typeconverters

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import ru.mertsalovda.countries.models.data.Languages
import ru.mertsalovda.countries.repositories.room.typeconverters.LanguageConverter

class LanguageConverterTest {

    lateinit var list: List<Languages>
    var stringLanguages: String = "A,B,C,D;1,2,3,4"
    lateinit var converter: LanguageConverter

    @Before
    fun setUp() {
        list = listOf(
            Languages("A", "B", "C", "D"),
            Languages("1", "2", "3", "4")
        )
        converter = LanguageConverter()
    }

    @Test
    fun listToString() {
        assertTrue(converter.listToString(list) == stringLanguages)
    }

    @Test
    fun stringToList() {
        val stringToList = converter.stringToList(stringLanguages)
        assertTrue(list.containsAll(stringToList))
    }
}