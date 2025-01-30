package com.walmart.countriescodingchallenge.data.local

import com.walmart.countriescodingchallenge.data.remote.Country

data class LocalCountryModel(
    val name: String,
    val region: String,
    val capital: String,
    val code: String
) {
    companion object {

        fun fromApiResponse(country: Country): LocalCountryModel {
            return LocalCountryModel(
                name = country.name,
                region = country.region,
                capital = country.capital,
                code = country.code
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LocalCountryModel

        if (name != other.name) return false
        if (region != other.region) return false
        if (capital != other.capital) return false
        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + region.hashCode()
        result = 31 * result + capital.hashCode()
        result = 31 * result + code.hashCode()
        return result
    }
}