package com.campmap.fastdrs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.campmap.fastdrs.domain.model.Screening

@Database(entities = [Screening::class], version = 1)
abstract class ScreeningDatabase : RoomDatabase() {
    // abstract fun screeningDao(): ScreeningDao
}
