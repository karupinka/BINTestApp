package ru.yandex.repinanr.binapp.domain.model

data class BinHistoryItem(
    val id: Long,
    val bin: String,
    val statusSuccess: Boolean
)
