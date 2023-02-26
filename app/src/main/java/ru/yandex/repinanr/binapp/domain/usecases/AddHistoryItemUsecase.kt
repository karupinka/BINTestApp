package ru.yandex.repinanr.binapp.domain.usecases

import ru.yandex.repinanr.binapp.domain.BinRepository
import ru.yandex.repinanr.binapp.domain.model.BinInfo
import javax.inject.Inject

class AddHistoryItemUsecase @Inject constructor(
    private val repository: BinRepository
) {
    suspend operator fun invoke(binInfo: BinInfo) = repository.addBinHistoryItem(binInfo)
}
