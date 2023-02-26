package ru.yandex.repinanr.binapp.domain.mapper

import ru.yandex.repinanr.binapp.data.model.entity.BinHistoryEntity
import ru.yandex.repinanr.binapp.data.model.response.BinInfoResponse
import ru.yandex.repinanr.binapp.domain.model.BinHistoryItem
import ru.yandex.repinanr.binapp.domain.model.BinInfo
import ru.yandex.repinanr.binapp.domain.model.BinNumber
import ru.yandex.repinanr.binapp.domain.model.Country
import javax.inject.Inject

class BinMapper @Inject constructor() {

    fun mapBinResponseToBinModel(bin: String, response: BinInfoResponse): BinInfo {
        return BinInfo(
            bin = bin,
            number = BinNumber(
                response.number.length,
                response.number.luhn
            ),
            scheme = response.scheme,
            type = response.type,
            brand = response.brand,
            prepaid = response.prepaid,
            country = Country(
                response.country.numeric,
                response.country.alpha,
                response.country.name,
                response.country.emoji,
                response.country.currency,
                response.country.latitude,
                response.country.longitude
            )
        )
    }

    fun mapBinModelToHistoryEntity(binInfo: BinInfo): BinHistoryEntity {
        return BinHistoryEntity(
            bin = binInfo.bin,
            cardScheme = binInfo.scheme,
            cardType = binInfo.type
        )
    }

    private fun mapHistoryEntityToHistoryModel(historyEntity: BinHistoryEntity): BinHistoryItem {
        return BinHistoryItem(
            id = historyEntity.id,
            bin = historyEntity.bin,
            cardScheme = historyEntity.cardScheme,
            cardType = historyEntity.cardType
        )
    }

    fun mapListHistoryEntityToListHistoryModel(historyEntities: List<BinHistoryEntity>): List<BinHistoryItem> {
        return historyEntities.map { mapHistoryEntityToHistoryModel(it) }
    }
}
