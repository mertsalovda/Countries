package ru.mertsalovda.countries.models.data

import com.google.gson.annotations.SerializedName

data class Country(
	@SerializedName("name") val name: String,
	@SerializedName("flag") val flag: String,
	@SerializedName("currencies") val currencies: List<Currencies>,
	@SerializedName("languages") val languages: List<Languages>,
	@SerializedName("timezones") val timezones: List<String>
)
