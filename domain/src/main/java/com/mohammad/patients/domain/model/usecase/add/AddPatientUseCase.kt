package com.mohammad.patients.domain.model.usecase.add

import com.mohammad.patients.domain.model.add.AddPatientRemoteModel
import com.mohammad.patients.domain.model.add.BodyAddPatientModel
import com.mohammad.patients.repo.PatientsRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel{
        return repository.addPatients(bodyAddPatientModel)
    }
}