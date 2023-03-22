package com.mohammad.patients.domain.model.patients

import com.google.gson.annotations.SerializedName

data class TestModel(

    @SerializedName("_id")
    val id: String,

    val type: String,

    val reading: String,

    val date: String,
)
