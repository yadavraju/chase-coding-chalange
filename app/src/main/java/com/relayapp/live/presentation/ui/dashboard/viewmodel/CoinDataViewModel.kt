package com.relayapp.live.presentation.ui.dashboard.viewmodel

import androidx.lifecycle.viewModelScope
import com.relayapp.live.data.model.coinresponse.CoinResponse
import com.relayapp.live.data.model.liveresponse.RoomDataResponse
import com.relayapp.live.domain.exception.BaseException
import com.relayapp.live.domain.usecase.coin.CoinDataUseCase
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

data class CoinViewSate(
    override val isLoading: Boolean = false,
    override val exception: BaseException? = null,
    val response: CoinResponse? = null
) : ViewState(isLoading, exception)

@HiltViewModel
class CoinDataViewModel @Inject constructor(
    private val roomDataUseCase: CoinDataUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(CoinViewSate(isLoading = false))
    override val state: StateFlow<CoinViewSate>
        get() = _state

    init {
        getCoinData()
    }

    private fun getCoinData() = safeLunch {
        showLoading()
        roomDataUseCase.invoke(Unit)
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