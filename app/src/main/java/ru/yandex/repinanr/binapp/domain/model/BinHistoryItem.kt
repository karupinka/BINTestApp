package ru.yandex.repinanr.binapp.domain.model

data class BinHistoryItem(
    val id: Long,
    val bin: String,
    val cardType: String,
    val cardScheme: String
)
