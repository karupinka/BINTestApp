package ru.yandex.repinanr.binapp.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.yandex.repinanr.binapp.R
import ru.yandex.repinanr.binapp.domain.model.BinHistoryItem
import javax.inject.Inject

class HistoryAdapter @Inject constructor(): ListAdapter<BinHistoryItem, HistoryViewHolder>(
    HistoryDiffUtilCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}