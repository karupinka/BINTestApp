package ru.yandex.repinanr.binapp.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.yandex.repinanr.binapp.domain.model.BinHistoryItem
import ru.yandex.repinanr.binapp.domain.usecases.ClearHistoryUsecase
import ru.yandex.repinanr.binapp.domain.usecases.GetHistoryUsecase
import ru.yandex.repinanr.binapp.presentation.EmptyResultState
import ru.yandex.repinanr.binapp.presentation.InProgress
import ru.yandex.repinanr.binapp.presentation.ResultState
import ru.yandex.repinanr.binapp.presentation.State
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getHistoryUsecase: GetHistoryUsecase,
    private val clearHistoryUsecase: ClearHistoryUsecase
) : ViewModel() {
    private val _historyList = MutableLiveData<List<BinHistoryItem>>()
    val historyList: LiveData<List<BinHistoryItem>>
        get() = _historyList

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun clearHistory() {
        _state.value = InProgress
        viewModelScope.launch {
            clearHistoryUsecase()
            _state.value = ResultState
            _historyList.value = listOf()
        }
    }

    fun getHistory() {
        _state.value = InProgress
        viewModelScope.launch {
            val list = getHistoryUsecase()
            _state.value = if (list.isNotEmpty()) ResultState else EmptyResultState
            _historyList.value = list
        }
    }
}