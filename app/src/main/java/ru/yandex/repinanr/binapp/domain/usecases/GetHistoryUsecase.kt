package ru.yandex.repinanr.binapp.domain.usecases

import ru.yandex.repinanr.binapp.domain.BinRepository
import javax.inject.Inject

class GetHistoryUsecase @Inject constructor(
    private val repository: BinRepository
) {
    suspend operator fun invoke() = repository.getBinHistory()
}
