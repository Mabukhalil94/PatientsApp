package com.mohammad.patients.repo

import com.mohammad.patients.domain.model.add.AddPatientRemoteModel
import com.mohammad.patients.domain.model.add.BodyAddPatientModel
import com.mohammad.patients.domain.model.patients.PatientsRemoteModel

interface PatientsRepository {

    suspend fun getPatients(): List<PatientsRemoteModel>

    suspend fun addPatients(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel
}