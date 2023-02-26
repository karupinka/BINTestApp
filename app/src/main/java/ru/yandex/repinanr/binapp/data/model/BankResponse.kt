package ru.yandex.repinanr.binapp.data.model

import com.google.gson.annotations.SerializedName

data class BankResponse(
    val name: String,
    val url: String,
    val phone: String,
    val city: String
)
