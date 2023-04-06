package com.mohammad.patients.data.repository

import android.util.Log
import com.mohammad.patients.data.datasource.PatientsDataSource
import com.mohammad.patients.domain.model.add.AddPatientRemoteModel
import com.mohammad.patients.domain.model.add.BodyAddPatientModel
import com.mohammad.patients.domain.model.delete.PatientDeleteResponseModel
import com.mohammad.patients.domain.model.patients.PatientsRemoteModel
import com.mohammad.patients.repo.PatientsRepository
import javax.inject.Inject

class PatientsRepositoryImp @Inject constructor(private val patientsDataSource: PatientsDataSource) : PatientsRepository {

    override suspend fun getPatients(): List<PatientsRemoteModel> {
       // Log.e("TagP", "getPatients: ")
        val listSorted =  patientsDataSource.getPatients().data
        return listSorted

    }

    override suspend fun addPatients(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel {
       return patientsDataSource.addPatients(bodyAddPatientModel)
    }

    override suspend fun deletePatients(id: String): PatientDeleteResponseModel {
        return patientsDataSource.deletePatients(id)
    }

    override suspend fun getPatientById(id: String): PatientsRemoteModel {
        return patientsDataSource.getPatientById(id).data
    }
}