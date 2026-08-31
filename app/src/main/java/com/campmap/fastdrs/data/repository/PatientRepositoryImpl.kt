package com.campmap.fastdrs.data.repository

import com.campmap.fastdrs.data.local.PatientDao
import com.campmap.fastdrs.domain.model.Patient

class PatientRepositoryImpl(private val patientDao: PatientDao) {
    suspend fun addPatient(patient: Patient) {
        patientDao.insert(patient)
    }

    suspend fun getAllPatients(): List<Patient> {
        return patientDao.getAllPatients()
    }
}
