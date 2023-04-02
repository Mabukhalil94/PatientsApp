package com.mohammad.patients.domain.model.add

data class AddPatientRemoteModel(
    val condition: String,

    val _id: Int,

    val name: String,

    val address: String,

    val mobile: String,

    val email: String,

    val birthdate: String,

    val gender: String,

    val photo: String,

    val tests: String,

    val createdAt :String,

    val  updatedAt :String,

    val  __v :String,

    )
