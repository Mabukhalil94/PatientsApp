package com.mohammad.patients.data.di

import com.mohammad.patients.data.datasource.PatientsDataSource
import com.mohammad.patients.data.repository.PatientsRepositoryImp
import com.mohammad.patients.repo.PatientsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

     @Provides
    fun providePatientsRepository(dataSource: PatientsDataSource): PatientsRepository {
        return PatientsRepositoryImp(dataSource)
    }
}