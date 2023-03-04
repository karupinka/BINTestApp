package ru.yandex.repinanr.binapp.presentation.history

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.yandex.repinanr.binapp.R
import ru.yandex.repinanr.binapp.databinding.HistoryItemBinding
import ru.yandex.repinanr.binapp.domain.model.BinHistoryItem

class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var binding: HistoryItemBinding

    fun bind(historyItem: BinHistoryItem, position: Int) {
        binding = HistoryItemBinding.bind(itemView)

        with(binding) {
            title.text = historyItem.bin
            status.text = if(historyItem.statusSuccess) {
                itemView.context.getString(R.string.success_status)
            } else {
                itemView.context.getString(R.string.not_found_status)
            }
        }
    }
}