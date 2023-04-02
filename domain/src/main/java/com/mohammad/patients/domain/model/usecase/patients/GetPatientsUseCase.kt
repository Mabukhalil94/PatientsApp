package com.mohammad.patients.domain.model.usecase.patients

import com.mohammad.patients.domain.model.patients.PatientsRemoteModel
import com.mohammad.patients.repo.PatientsRepository
import javax.inject.Inject

class GetPatientsUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend fun invoke(): List<PatientsRemoteModel>{
        return repository.getPatients()
    }
}