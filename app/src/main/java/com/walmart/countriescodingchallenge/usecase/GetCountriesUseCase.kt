package com.walmart.countriescodingchallenge.usecase

import com.walmart.countriescodingchallenge.data.local.LocalCountryModel
import com.walmart.countriescodingchallenge.repository.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor (
    private val repository: CountriesRepository
) {
    suspend operator fun invoke(): Result<List<LocalCountryModel>> = withContext(Dispatchers.IO) {
        return@withContext repository.fetchCountries().mapCatching { countries ->
            countries.map { LocalCountryModel.fromApiResponse(it) }
        }
    }
}