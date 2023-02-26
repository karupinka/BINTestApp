package ru.yandex.repinanr.binapp.domain.model

data class Country(
    val numeric: String,
    val alpha: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Double,
    val longitude: String
)
