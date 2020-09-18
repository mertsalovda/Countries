package ru.mertsalovda.countries.repositories.room.typeconverters

import org.junit.Test

import org.junit.Assert.*

class TimezoneConverterTest {

    private val list = listOf<String>("A", "B", "C")
    private val string = "A,B,C"
    private val converter = TimezoneConverter()

    @Test
    fun listToString() {
        assertTrue(converter.listToString(list) == string)
    }

    @Test
    fun stringToList() {
        assertTrue(list.containsAll(converter.stringToList(string)))
    }
}