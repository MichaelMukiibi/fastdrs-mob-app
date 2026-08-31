package com.campmap.fastdrs.domain.model

data class Patient(
    val id: String,
    val age: Int,
    val sex: String,
    val createdAt: Long = System.currentTimeMillis()
)
