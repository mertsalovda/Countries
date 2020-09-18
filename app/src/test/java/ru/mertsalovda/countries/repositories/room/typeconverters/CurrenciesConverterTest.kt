package ru.mertsalovda.countries.repositories.room.typeconverters

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.mertsalovda.countries.models.data.Currencies

class CurrenciesConverterTest {

    lateinit var list: List<Currencies>
    var stringCurrencies: String = "A,B,C;1,2,3"
    lateinit var converter: CurrenciesConverter

    @Before
    fun setUp() {
        list = listOf(
            Currencies("A", "B", "C"),
            Currencies("1", "2", "3")
        )
        converter = CurrenciesConverter()
    }

    @Test
    fun listToString() {
        assertTrue(converter.listToString(list) == stringCurrencies)
    }

    @Test
    fun stringToList() {
        val stringToList = converter.stringToList(stringCurrencies)
        assertTrue(list.containsAll(stringToList))
    }
}