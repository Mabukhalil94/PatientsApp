package com.mohammad.patients.data.datasource

import com.mohammad.patients.domain.model.add.AddPatientRemoteModel
import com.mohammad.patients.domain.model.add.BodyAddPatientModel
import com.mohammad.patients.domain.model.delete.PatientDeleteResponseModel
import com.mohammad.patients.domain.model.details.DetailsPatientWrapperRemoteModel
import com.mohammad.patients.domain.model.patients.PatientsWrapperRemoteModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrapperRemoteModel

    @POST("patients")
    suspend fun addPatients(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel

    @DELETE("patients/{id}")
    suspend fun deletePatients(@Path("id") id: String): PatientDeleteResponseModel

    @GET("patients/{id}")
    suspend fun getPatientById(@Path("id") id: String): DetailsPatientWrapperRemoteModel
}