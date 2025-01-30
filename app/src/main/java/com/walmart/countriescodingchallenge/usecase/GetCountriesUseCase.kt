package com.walmart.countriescodingchallenge.usecase

import com.walmart.countriescodingchallenge.data.local.LocalCountryModel
import com.walmart.countriescodingchallenge.repository.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor (
    private val repository: CountriesRepository
) {

    suspend operator fun invoke(): List<LocalCountryModel> = withContext(Dispatchers.IO) {
        repository.fetchCountries()
            .map { LocalCountryModel.fromApiResponse(it) }
    }
}