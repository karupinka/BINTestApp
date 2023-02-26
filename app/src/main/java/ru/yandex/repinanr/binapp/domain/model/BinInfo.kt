package ru.yandex.repinanr.binapp.domain.model

data class BinInfo(
    val bin: String,
    val number: BinNumber,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: Country
)
