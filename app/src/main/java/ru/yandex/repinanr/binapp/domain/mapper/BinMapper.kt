package ru.yandex.repinanr.binapp.domain.mapper

import ru.yandex.repinanr.binapp.data.model.entity.BinHistoryEntity
import ru.yandex.repinanr.binapp.data.model.response.BinInfoResponse
import ru.yandex.repinanr.binapp.domain.model.*
import javax.inject.Inject

class BinMapper @Inject constructor() {

    fun mapBinResponseToBinModel(bin: String, response: BinInfoResponse): BinInfo {
        return BinInfo(
            bin = bin,
            number = response.number?.let {
                BinNumber(
                    it.length,
                    it.luhn
                )
            },
            scheme = response.scheme,
            type = response.type,
            brand = response.brand,
            prepaid = response.prepaid,
            country = response.country?.let {
                Country(
                    it.numeric,
                    it.alpha,
                    it.name,
                    it.emoji,
                    it.currency,
                    it.latitude,
                    it.longitude
                )
            },
            bank = response.bank?.let {
                Bank(
                    it.name,
                    it.url,
                    it.phone,
                    it.city
                )
            }
        )
    }

    fun mapBinModelToHistoryEntity(binInfo: BinInfo): BinHistoryEntity {
        return BinHistoryEntity(
            bin = binInfo.bin,
            succeeded = 1
        )
    }

    fun mapBinErrorToHistoryEntity(bin: String): BinHistoryEntity {
        return BinHistoryEntity(
            bin = bin,
            succeeded = 0
        )
    }

    private fun mapHistoryEntityToHistoryModel(historyEntity: BinHistoryEntity): BinHistoryItem {
        return BinHistoryItem(
            id = historyEntity.id,
            bin = historyEntity.bin,
            historyEntity.succeeded == 1
        )
    }

    fun mapListHistoryEntityToListHistoryModel(historyEntities: List<BinHistoryEntity>): List<BinHistoryItem> {
        return historyEntities.map { mapHistoryEntityToHistoryModel(it) }
    }
}
