package com.relayapp.live.presentation.ui.home

import com.relayapp.live.domain.annotation.Action
import com.relayapp.live.domain.exception.BaseException
import com.relayapp.live.domain.model.Dialog
import com.relayapp.live.domain.usecase.weather.GetCurrentLocationUseCase
import com.relayapp.live.domain.usecase.weather.GetCurrentWeatherByCityUseCase
import com.relayapp.live.domain.usecase.weather.GetCurrentWeatherByLocationUseCase
import com.relayapp.live.domain.usecase.weather.GetLastCityUseCase
import com.relayapp.live.presentation.base.BaseViewModel
import com.relayapp.live.presentation.base.ViewState
import com.relayapp.live.presentation.base.toBaseException
import com.relayapp.live.presentation.model.CurrentWeatherMapper
import com.relayapp.live.presentation.model.CurrentWeatherViewDataModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

sealed interface SearchState {
    val enabled: Boolean
    val query: String

    data class Changing(
        override val enabled: Boolean = true,
        override val query: String = ""
    ) : SearchState

    data class Closed(
        override val enabled: Boolean = false,
        override val query: String = ""
    ) : SearchState
}

data class HomeViewState(
    override val isLoading: Boolean = false,
    override val exception: BaseException? = null,
    val currentWeather: CurrentWeatherViewDataModel? = null,
    val searchState: SearchState = SearchState.Closed(),
    val isRequestPermission: Boolean = false,
) : ViewState(isLoading, exception)

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherByCityUseCase: GetCurrentWeatherByCityUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getCurrentWeatherByLocationUseCase: GetCurrentWeatherByLocationUseCase,
    private val weatherMapper: CurrentWeatherMapper,
    private val getLastCityUseCase: GetLastCityUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(HomeViewState(isLoading = true))
    override val state: StateFlow<HomeViewState>
        get() = _state

    init {
        safeLunch {
            getLastCityUseCase.invoke().collect { city ->
                if (city.isBlank()) {
                    _state.update { it.copy(isRequestPermission = true) }
                } else {
                    getWeather(city)
                }
            }
        }
    }

    fun getWeather(city: String) {
        showLoading()
        safeLunch {
            getCurrentWeatherByCityUseCase.invoke(GetCurrentWeatherByCityUseCase.Params(city))
                .catch { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            exception = throwable.toBaseException()
                        )
                    }
                }
                .map { weather -> weatherMapper.mapperToViewDataModel(weather) }
                .collect { weather ->
                    _state.update {
                        it.copy(isLoading = false, currentWeather = weather)
                    }
                }
        }
    }

    fun getCurrentLocation() {
        safeLunch {
            showLoading()
            getCurrentLocationUseCase().collect {
                getWeatherByLatLang(it)
            }
        }
    }


    private fun getWeatherByLatLang(latLang: LatLng) = safeLunch {
        getCurrentWeatherByLocationUseCase.invoke(GetCurrentWeatherByLocationUseCase.Params(latLang))
            .catch { throwable ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        exception = throwable.toBaseException()
                    )
                }
            }
            .map { weather -> weatherMapper.mapperToViewDataModel(weather) }
            .collect { weather ->
                _state.update {
                    it.copy(isLoading = false, currentWeather = weather)
                }
            }

    }

    private fun showLoading() {
        _state.update {
            it.copy(isLoading = true)
        }
    }

    override fun hideLoading() {
        _state.update {
            it.copy(isLoading = false)
        }
    }

    /**
     * Notify that the user when typing the search input
     */
    fun onSearchInputChanged(searchInput: String) {
        _state.update {
            it.copy(searchState = SearchState.Changing(query = searchInput))
        }
    }

    /**
     * Enable or disable search view
     */
    fun enableSearchView(enabled: Boolean) {
        _state.update { state ->
            state.copy(
                searchState = if (enabled) SearchState.Changing() else SearchState.Closed(
                    query = state.searchState.query
                )
            )
        }
    }

    fun permissionIsNotGranted() {
        val error = BaseException.DialogException(
            code = -1, dialog = Dialog(
                title = "Error with Permission",
                message = "Permission is not granted! Please grant the permission to continue to using app!",
                positiveMessage = "Open Setting",
                negativeMessage = "Cancel",
                positiveAction = Action.PERMISSION,
            )
        )
        showError(error)
    }

    override fun showError(error: BaseException) {
        if (_state.value.exception == null) {
            _state.update {
                it.copy(isLoading = false, exception = error)
            }
        }
    }

    fun cleanEvent() {
        _state.update {
            it.copy(isRequestPermission = false)
        }
    }
}
