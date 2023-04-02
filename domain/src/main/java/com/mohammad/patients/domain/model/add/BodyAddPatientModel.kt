package com.mohammad.patients.domain.model.add

import com.google.gson.annotations.SerializedName

class BodyAddPatientModel (

    val name: String,

    val address: String,

    @SerializedName( "birthdate" )
    val birthday: String,

    val gender: String,

    val mobile: String,

    val email: String,

        )
