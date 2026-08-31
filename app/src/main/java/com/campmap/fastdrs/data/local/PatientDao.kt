package com.campmap.fastdrs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.campmap.fastdrs.domain.model.Patient

@Dao
interface PatientDao {
    @Insert
    suspend fun insert(patient: Patient)

    @Query("SELECT * FROM Patient")
    suspend fun getAllPatients(): List<Patient>
}
