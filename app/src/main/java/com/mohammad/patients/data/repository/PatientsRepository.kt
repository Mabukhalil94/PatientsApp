package com.mohammad.patients.data.repository

import com.mohammad.patients.data.datasource.PatientsDataSource
import com.mohammad.patients.domain.model.patients.PatientsRemoteModel
import javax.inject.Inject

class PatientsRepository @Inject constructor(private val patientsDataSource: PatientsDataSource) {

    suspend fun getPatients(): List<PatientsRemoteModel> {
        val listSorted =  patientsDataSource.getPatients().data.sortedBy { it.PatientsName }
        return listSorted

    }
}