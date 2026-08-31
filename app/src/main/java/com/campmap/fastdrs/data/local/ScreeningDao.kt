package com.campmap.fastdrs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.campmap.fastdrs.domain.model.Screening

@Dao
interface ScreeningDao {
    @Insert
    suspend fun insert(screening: Screening)

    @Query("SELECT * FROM Screening ORDER BY timestamp DESC")
    suspend fun getAllScreenings(): List<Screening>

    @Query("SELECT * FROM Screening WHERE patientId = :patientId ORDER BY timestamp DESC")
    suspend fun getScreeningsForPatient(patientId: String): List<Screening>
}
