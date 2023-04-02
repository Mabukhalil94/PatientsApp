package com.mohammad.patients.domain.model.patients

import com.google.gson.annotations.SerializedName

data class PatientsRemoteModel(

    val condition: String,

    @SerializedName("_id")
    val id: String,

    @SerializedName("name")
    val PatientsName: String,

    val address: String,

    val mobile: String,

    val email: String,

    @SerializedName("birthdate")
    val birthday: String,

    val gender: String,

    val photo: String,

    val test: List<TestModel>,

    var selected: Boolean=false

)
