package com.mohammad.patients.data.di

import com.mohammad.patients.data.datasource.PatientsDataSource
import com.mohammad.patients.data.repository.PatientsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val baseUrl="https://patients-app-api.herokuapp.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePatientsDatasource(retrofit: Retrofit): PatientsDataSource {
        return retrofit.create(PatientsDataSource::class.java)

    }

   /* @Provides
    @Singleton
    fun provideRepository(dataSource: PatientsDataSource): PatientsRepository{
        return PatientsRepository(dataSource)
    }*/
}