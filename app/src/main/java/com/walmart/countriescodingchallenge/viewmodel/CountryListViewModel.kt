package com.walmart.countriescodingchallenge.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walmart.countriescodingchallenge.data.local.LocalCountryModel
import com.walmart.countriescodingchallenge.usecase.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class CountryListViewModel
@Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _progressFlag = MutableLiveData<Boolean>()
    val progressBarFlag: LiveData<Boolean> = _progressFlag

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        println("CountryListViewModel exception:$exception")
    }

    @VisibleForTesting
    var ioContext = Dispatchers.IO + errorHandler

    fun getCountries() {
        viewModelScope.launch (ioContext) {
            _progressFlag.postValue(true)
            runCatching {
                val countryList = getCountriesUseCase.invoke()
                _uiState.postValue(UiState.PopulateList(countryList))
            }.onFailure {
                _uiState.postValue(UiState.Error(it.localizedMessage))
            }
            _progressFlag.postValue(false)
        }
    }

    sealed class UiState {
        data class PopulateList(val list: List<LocalCountryModel>): UiState()
        data class Error(val errorMessage: String?): UiState()
    }
}