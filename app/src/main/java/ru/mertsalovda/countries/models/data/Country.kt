package ru.mertsalovda.countries.models.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country")
data class Country(
	@PrimaryKey
	@SerializedName("name") val name: String,
	@SerializedName("flag") val flag: String,

	@SerializedName("currencies")
	val currencies: List<Currencies>,

	@SerializedName("languages")
	val languages: List<Languages>,

	@SerializedName("timezones")
	val timezones: List<String>
)
