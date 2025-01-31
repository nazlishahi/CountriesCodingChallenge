package com.walmart.countriescodingchallenge

import com.walmart.countriescodingchallenge.data.remote.Country
import com.walmart.countriescodingchallenge.repository.CountriesRepository
import com.walmart.countriescodingchallenge.service.ApiService
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
class CountriesRepositoryTest {

    @Mock
    lateinit var apiService: ApiService

    private lateinit var countriesRepository: CountriesRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        countriesRepository = CountriesRepository(apiService)
    }

    @Test
    fun `fetchCountries should return a list of countries when successful`() = runTest {
        val mockResponse = listOf(Country("United States of America", "N.A.", "US", "Washington, D.C."))
        whenever(apiService.getCountryList()).thenReturn(mockResponse)

        val result = countriesRepository.fetchCountries()

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(mockResponse, result.getOrDefault(listOf()))
    }

    @Test
    fun `fetchCountries should throw an error when the API call fails`() = runTest {
        whenever(apiService.getCountryList()).thenThrow(RuntimeException("Network Error"))

        val result = countriesRepository.fetchCountries()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals("Network Error", result.exceptionOrNull()?.message)
    }
}