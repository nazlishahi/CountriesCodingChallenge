package com.walmart.countriescodingchallenge.repository

import com.walmart.countriescodingchallenge.data.remote.Country
import com.walmart.countriescodingchallenge.service.ApiService
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchCountries(): List<Country> {
        return apiService.getCountryList()
    }
}