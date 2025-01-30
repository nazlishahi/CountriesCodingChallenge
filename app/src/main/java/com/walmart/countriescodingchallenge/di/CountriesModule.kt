package com.walmart.countriescodingchallenge.di

import com.walmart.countriescodingchallenge.repository.CountriesRepository
import com.walmart.countriescodingchallenge.service.ApiService
import com.walmart.countriescodingchallenge.usecase.GetCountriesUseCase
import com.walmart.countriescodingchallenge.viewmodel.CountryListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountriesModule {

    @Singleton
    @Provides
    fun provideRepository(
        apiService: ApiService
    ): CountriesRepository {
        return CountriesRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideGetCountriesUseCase(repository: CountriesRepository): GetCountriesUseCase {
        return GetCountriesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideViewModel(
        countriesUseCase: GetCountriesUseCase
    ): CountryListViewModel {
        return CountryListViewModel(
            countriesUseCase
        )
    }
}