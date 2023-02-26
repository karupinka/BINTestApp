package ru.yandex.repinanr.binapp.data.model.response

data class BinInfoResponse(
    val number: BinNumberResponse,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: CountryResponse
)
