package com.mohammad.patients.domain.model.usecase.details

import com.mohammad.patients.domain.model.patients.PatientsRemoteModel
import com.mohammad.patients.repo.PatientsRepository
import javax.inject.Inject

class GetPatientByIdUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(id : String): List<PatientsRemoteModel> {
        return listOf(repository.getPatientById(id))
    }
}
