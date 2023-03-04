package ru.yandex.repinanr.binapp.presentation.history

import androidx.annotation.StringRes
import ru.yandex.repinanr.binapp.R

enum class SearchStatus(@StringRes val title: Int) {
    SUCCESS(R.string.success_status),
    NOT_FOUND(R.string.not_found_status)
}