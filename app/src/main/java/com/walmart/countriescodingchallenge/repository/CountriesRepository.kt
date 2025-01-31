package com.walmart.countriescodingchallenge.repository

import com.walmart.countriescodingchallenge.data.remote.Country
import com.walmart.countriescodingchallenge.service.ApiService
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchCountries(): Result<List<Country>> {
        return try {
            val response = apiService.getCountryList()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}