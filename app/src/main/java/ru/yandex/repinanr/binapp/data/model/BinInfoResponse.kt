package ru.yandex.repinanr.binapp.data.model

import com.google.gson.annotations.SerializedName

data class BinInfoResponse(
    val number: BinNumberResponse,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: CountryResponse,
    val avatar: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("social_insurance_number")
    val insuranceNumber: String,
    @SerializedName("date_of_birth")
    val birthDate: String
)
