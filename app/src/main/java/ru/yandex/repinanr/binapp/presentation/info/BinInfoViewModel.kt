package ru.yandex.repinanr.binapp.presentation.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.yandex.repinanr.binapp.R
import ru.yandex.repinanr.binapp.domain.model.BinInfo
import ru.yandex.repinanr.binapp.domain.usecases.AddHistoryItemErrorUsecase
import ru.yandex.repinanr.binapp.domain.usecases.AddHistoryItemUsecase
import ru.yandex.repinanr.binapp.domain.usecases.GetBinInfoUsecase
import ru.yandex.repinanr.binapp.presentation.ErrorState
import ru.yandex.repinanr.binapp.presentation.InProgress
import ru.yandex.repinanr.binapp.presentation.ResultState
import ru.yandex.repinanr.binapp.presentation.State
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class BinInfoViewModel @Inject constructor(
    private val addHistoryItemUsecase: AddHistoryItemUsecase,
    private val addHistoryItemErrorUsecase: AddHistoryItemErrorUsecase,
    private val getBinInfoUsecase: GetBinInfoUsecase
) : ViewModel() {
    private val _binInfo = MutableLiveData<BinInfo?>()
    val binInfo: LiveData<BinInfo?>
        get() = _binInfo

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private suspend fun addHistoryItemUsecase() {
        _binInfo.value?.let { addHistoryItemUsecase(it) }
    }

    private fun isBinValid(bin: String): Boolean {
        if (bin.isEmpty()) {
            _state.value = ErrorState(R.string.empty_string)
            return false
        } else if (bin.length != BIN_LENGTH) {
            _state.value = ErrorState(R.string.wrong_length_string)
            return false
        } else {
            return true
        }
    }

    fun getHistoryItemUsecase(bin: String) {
        if (isBinValid(bin)) {
            _state.value = InProgress
            viewModelScope.launch {
                try {
                    getBinInfoUsecase(bin)?.let { _binInfo.value = it }
                    addHistoryItemUsecase()
                    _state.value = ResultState
                } catch (e: UnknownHostException) {
                    _state.value = ErrorState(R.string.network_error)
                } catch (e: SocketTimeoutException) {
                    _state.value = ErrorState(R.string.network_error)
                } catch (e: ConnectException) {
                    _state.value = ErrorState(R.string.network_error)
                } catch (e: HttpException) {
                    when (e.code()) {
                        HttpURLConnection.HTTP_NOT_FOUND -> {
                            _state.value = ErrorState(R.string.not_found_error)
                        }
                        else -> {
                            _state.value = ErrorState(R.string.other_error)
                        }
                    }
                    addHistoryItemErrorUsecase(bin)
                } catch (e: java.lang.Exception) {
                    _state.value = ErrorState(R.string.other_error)
                }
            }
        }
    }

    companion object {
        const val BIN_LENGTH = 6
    }
}