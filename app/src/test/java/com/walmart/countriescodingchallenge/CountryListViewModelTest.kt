package com.walmart.countriescodingchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.walmart.countriescodingchallenge.data.local.LocalCountryModel
import com.walmart.countriescodingchallenge.usecase.GetCountriesUseCase
import com.walmart.countriescodingchallenge.viewmodel.CountryListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.KArgumentCaptor
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class CountryListViewModelTest {

    private lateinit var viewModel: CountryListViewModel

    @Mock
    lateinit var progressFlagObserver: Observer<Boolean>

    @Mock
    lateinit var uiStateObserver: Observer<CountryListViewModel.UiState>

    @Mock
    lateinit var getCountriesUseCase: GetCountriesUseCase

    private lateinit var uiStateCaptor: KArgumentCaptor<CountryListViewModel.UiState>

    private lateinit var progressFlagCaptor: KArgumentCaptor<Boolean>

    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        viewModel = CountryListViewModel(getCountriesUseCase)
        viewModel.ioContext = testDispatcher

        uiStateCaptor = argumentCaptor<CountryListViewModel.UiState>()
        progressFlagCaptor = argumentCaptor<Boolean>()

        viewModel.progressBarFlag.observeForever(progressFlagObserver)
        viewModel.uiState.observeForever(uiStateObserver)
    }

    @After
    fun tearDown() {
        viewModel.progressBarFlag.removeObserver(progressFlagObserver)
        viewModel.uiState.removeObserver(uiStateObserver)
        Dispatchers.resetMain()
    }

    @Test
    fun testGetCountries_success() = runTest {
        val mockCountryList = listOf(LocalCountryModel("United States of America", "N.A.", "Washington, D.C.", "US"))
        whenever(getCountriesUseCase.invoke()).thenReturn(Result.success(mockCountryList))

        viewModel.getCountries()

        Mockito.verify(progressFlagObserver, times(2))
            .onChanged(progressFlagCaptor.capture())

        Mockito.verify(getCountriesUseCase, times(1)).invoke()

        Mockito.verify(uiStateObserver, times(1))
            .onChanged(uiStateCaptor.capture())

        Assert.assertEquals(progressFlagCaptor.allValues.size, 2)
        Assert.assertEquals(progressFlagCaptor.allValues[0], true)
        Assert.assertEquals(progressFlagCaptor.allValues[1], false)

        Assert.assertEquals(uiStateCaptor.allValues.size, 1)
        Assert.assertTrue(uiStateCaptor.allValues[0] is CountryListViewModel.UiState.PopulateList)
    }

    @Test
    fun testGetCountries_failure() = runTest {
        val mockThrowable = mock<Throwable>()
        whenever(getCountriesUseCase.invoke()).thenReturn(Result.failure(mockThrowable))
        viewModel.getCountries()

        Mockito.verify(progressFlagObserver, times(2))
            .onChanged(progressFlagCaptor.capture())

        Mockito.verify(getCountriesUseCase, times(1)).invoke()

        Mockito.verify(uiStateObserver, times(1))
            .onChanged(uiStateCaptor.capture())

        Assert.assertEquals(progressFlagCaptor.allValues.size, 2)
        Assert.assertEquals(progressFlagCaptor.allValues[0], true)
        Assert.assertEquals(progressFlagCaptor.allValues[1], false)

        Assert.assertEquals(uiStateCaptor.allValues.size, 1)
        Assert.assertTrue(uiStateCaptor.allValues[0] is CountryListViewModel.UiState.Error)
    }
}