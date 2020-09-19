package ru.mertsalovda.countries.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.mertsalovda.countries.R

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Проверка на несуществующий текст в списке
     */
    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        onView(withId(R.id.rv_country_list))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Narnia"))
                )
            )
    }

    private val COUNTRY_NAME = "Russian Federation"

    /**
     * При вводе в поисковую строку в списке стран должна отобразиться нужная страна.
     * Передварительно выполнить настройки https://developer.android.com/training/testing/espresso/setup#set-up-environment
     */
    @Test
    fun search_by_country_name() {
        onView(withId(R.id.action_search))
            .check(matches(isDisplayed()))
            .perform(click())
        onView(withHint("Введите название страны"))
            .check(matches(isDisplayed()))
            .perform(typeTextIntoFocusedView(COUNTRY_NAME))
        onView(withId(R.id.rv_country_list))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText(COUNTRY_NAME))
                ), click()
            )
    }

    private val AFGHANISTAN_POSITION = 0
    private val AFGHANISTAN = "Afghanistan"

    /**
     * Нажать на первый элемент в списке и проверить, что открывась его детализация.
     */
    @Test
    fun open_country_details() {
        onView(withId(R.id.rv_country_list))
            .perform(actionOnItemAtPosition<CountriesAdapter.CountryViewHolder>(AFGHANISTAN_POSITION, click()))
        onView(withId(R.id.tv_name))
            .check(matches(withText(AFGHANISTAN)))
    }

}