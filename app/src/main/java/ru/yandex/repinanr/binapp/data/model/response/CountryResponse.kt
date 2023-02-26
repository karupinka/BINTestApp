package ru.yandex.repinanr.binapp.data.model.response

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    val numeric: String,
    @SerializedName("alpha2")
    val alpha: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Double,
    val longitude: String
)
