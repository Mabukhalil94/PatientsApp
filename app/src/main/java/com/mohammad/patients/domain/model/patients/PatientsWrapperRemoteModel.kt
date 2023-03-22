package com.mohammad.patients.domain.model.patients

data class PatientsWrapperRemoteModel(
    val status : Int,
    val message: String,
    val data : List<PatientsRemoteModel>
)