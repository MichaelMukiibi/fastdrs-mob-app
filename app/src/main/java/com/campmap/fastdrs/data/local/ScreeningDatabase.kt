package com.campmap.fastdrs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.campmap.fastdrs.domain.model.Screening
import com.campmap.fastdrs.domain.model.Patient

@Database(entities = [Screening::class, Patient::class], version = 1)
abstract class ScreeningDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun screeningDao(): ScreeningDao
}
