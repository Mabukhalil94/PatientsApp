package com.mohammad.patients.data.datasource

import com.mohammad.patients.domain.model.patients.PatientsWrapperRemoteModel
import retrofit2.http.GET

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrapperRemoteModel
}