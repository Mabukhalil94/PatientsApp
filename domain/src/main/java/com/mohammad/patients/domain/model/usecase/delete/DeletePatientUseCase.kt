package com.mohammad.patients.domain.model.usecase.delete

import com.mohammad.patients.domain.model.add.AddPatientRemoteModel
import com.mohammad.patients.domain.model.add.BodyAddPatientModel
import com.mohammad.patients.domain.model.delete.PatientDeleteResponseModel
import com.mohammad.patients.repo.PatientsRepository
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(id : String): PatientDeleteResponseModel {
        return repository.deletePatients(id)
    }
}