package com.mohammad.patients.repo

import com.mohammad.patients.domain.model.add.AddPatientRemoteModel
import com.mohammad.patients.domain.model.add.BodyAddPatientModel
import com.mohammad.patients.domain.model.delete.PatientDeleteResponseModel
import com.mohammad.patients.domain.model.details.DetailsPatientWrapperRemoteModel
import com.mohammad.patients.domain.model.patients.PatientsRemoteModel

interface PatientsRepository {

    suspend fun getPatients(): List<PatientsRemoteModel>

    suspend fun addPatients(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel

    suspend fun deletePatients(id : String): PatientDeleteResponseModel

    suspend fun getPatientById(id: String): PatientsRemoteModel
}