package com.mohammad.patients.data.datasource

import com.mohammad.patients.domain.model.add.AddPatientRemoteModel
import com.mohammad.patients.domain.model.add.BodyAddPatientModel
import com.mohammad.patients.domain.model.patients.PatientsWrapperRemoteModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrapperRemoteModel

    @POST("patients")
    suspend fun addPatients(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel
}