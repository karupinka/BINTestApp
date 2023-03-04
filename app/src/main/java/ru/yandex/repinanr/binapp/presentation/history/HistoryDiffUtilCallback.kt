package ru.yandex.repinanr.binapp.presentation.history

import androidx.recyclerview.widget.DiffUtil
import ru.yandex.repinanr.binapp.domain.model.BinHistoryItem

class HistoryDiffUtilCallback: DiffUtil.ItemCallback<BinHistoryItem>() {
    override fun areItemsTheSame(oldItem: BinHistoryItem, newItem: BinHistoryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BinHistoryItem, newItem: BinHistoryItem): Boolean {
        return oldItem == newItem
    }
}