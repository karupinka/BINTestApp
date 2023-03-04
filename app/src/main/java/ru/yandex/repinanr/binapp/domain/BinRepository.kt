package ru.yandex.repinanr.binapp.domain

import ru.yandex.repinanr.binapp.domain.model.BinHistoryItem
import ru.yandex.repinanr.binapp.domain.model.BinInfo

interface BinRepository {

    suspend fun getBinInfo(bin: String): BinInfo

    suspend fun clearBinHistory()

    suspend fun getBinHistory(): List<BinHistoryItem>

    suspend fun addBinHistoryItem(binInfo: BinInfo)

    suspend fun addBinHistoryErrorItem(bin: String)

}
