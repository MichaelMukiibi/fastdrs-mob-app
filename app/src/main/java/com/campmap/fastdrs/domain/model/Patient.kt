package com.campmap.fastdrs.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(
    @PrimaryKey val id: String,
    val age: Int,
    val sex: String,
    val createdAt: Long = System.currentTimeMillis()
)
