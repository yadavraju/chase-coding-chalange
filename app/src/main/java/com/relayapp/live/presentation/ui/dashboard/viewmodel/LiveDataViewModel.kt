package com.relayapp.live.presentation.ui.dashboard.viewmodel

import androidx.lifecycle.viewModelScope
import com.relayapp.live.data.model.liveresponse.RoomDataResponse
import com.relayapp.live.domain.exception.BaseException
import com.relayapp.live.domain.usecase.live.RoomDataUseCase
import com.relayapp.live.presentation.auth.AuthViewSate
import com.relayapp.live.presentation.base.BaseViewModel
import com.relayapp.live.presentation.base.ViewState
import com.relayapp.live.presentation.base.toBaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RoomViewSate(
    override val isLoading: Boolean = false,
    override val exception: BaseException? = null,
    val response: RoomDataResponse? = null
) : ViewState(isLoading, exception)

@HiltViewModel
class LiveDataViewModel @Inject constructor(
    private val roomDataUseCase: RoomDataUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(RoomViewSate(isLoading = false))
    override val state: StateFlow<RoomViewSate>
        get() = _state

    fun getRoomData(roomName: String, pageNumber: Int = 1) = safeLunch {
        showLoading()
        roomDataUseCase.invoke(RoomDataUseCase.Params(roomName, pageNumber))
            .catch { throwable ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        exception = throwable.toBaseException()
                    )
                }
            }.collect { response ->
                _state.update { it.copy(isLoading = false, response = response) }
            }
    }

    private fun showLoading() {
        _state.update { it.copy(isLoading = true) }
    }
}