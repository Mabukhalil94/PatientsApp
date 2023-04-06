package com.mohammad.patients.domain.model.details

import com.mohammad.patients.domain.model.patients.PatientsRemoteModel

data class DetailsPatientWrapperRemoteModel(
    val status : Int,
    val message: String,
    val data : PatientsRemoteModel
)
