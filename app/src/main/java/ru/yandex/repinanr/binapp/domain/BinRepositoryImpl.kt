package ru.yandex.repinanr.binapp.domain

import ru.yandex.repinanr.binapp.data.local.BinHistoryDao
import ru.yandex.repinanr.binapp.data.remote.BinService
import ru.yandex.repinanr.binapp.domain.mapper.BinMapper
import ru.yandex.repinanr.binapp.domain.model.BinHistoryItem
import ru.yandex.repinanr.binapp.domain.model.BinInfo
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    val mapper: BinMapper,
    val service: BinService,
    val historyDao: BinHistoryDao
): BinRepository {
    override suspend fun getBinInfo(bin: String): BinInfo {
        return mapper.mapBinResponseToBinModel(bin, service.getBinInfo(bin))
    }

    override suspend fun clearBinHistory() {
        historyDao.clearHistory()
    }

    override suspend fun getBinHistory(): List<BinHistoryItem> {
        return mapper.mapListHistoryEntityToListHistoryModel(historyDao.getHistory())
    }

    override suspend fun addBinHistoryItem(binInfo: BinInfo) {
        historyDao.addHistoryItem(mapper.mapBinModelToHistoryEntity(binInfo))
    }

    override suspend fun addBinHistoryErrorItem(bin: String) {
        historyDao.addHistoryItem(mapper.mapBinErrorToHistoryEntity(bin))
    }
}
