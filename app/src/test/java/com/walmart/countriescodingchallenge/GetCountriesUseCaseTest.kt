package com.walmart.countriescodingchallenge

import com.walmart.countriescodingchallenge.data.local.LocalCountryModel
import com.walmart.countriescodingchallenge.data.remote.Country
import com.walmart.countriescodingchallenge.repository.CountriesRepository
import com.walmart.countriescodingchallenge.usecase.GetCountriesUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetCountriesUseCaseTest {

    @Mock
    lateinit var countriesRepository: CountriesRepository

    private lateinit var getCountriesUseCase: GetCountriesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getCountriesUseCase = GetCountriesUseCase(countriesRepository)
    }

    @Test
    fun `test should return list of countries when repository succeeds`() = runTest {
        val mockCountries = listOf(Country("United Kingdom", "EU", "UK", "London"))
        val expectedResult = mockCountries.map { LocalCountryModel.fromApiResponse(it) }
        whenever(countriesRepository.fetchCountries()).thenReturn(mockCountries)

        val result = getCountriesUseCase.invoke()
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `test should return null when repository fails`() = runTest {
        whenever(countriesRepository.fetchCountries()).thenReturn(emptyList())

        val result = getCountriesUseCase.invoke()
        Assert.assertEquals(emptyList<LocalCountryModel>(), result)
    }
}