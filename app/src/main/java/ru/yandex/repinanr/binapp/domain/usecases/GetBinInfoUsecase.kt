package ru.yandex.repinanr.binapp.domain.usecases

import ru.yandex.repinanr.binapp.domain.BinRepository
import javax.inject.Inject

class GetBinInfoUsecase @Inject constructor(
    private val repository: BinRepository
) {
    suspend operator fun invoke(bin: String) = repository.getBinInfo(bin)
}
