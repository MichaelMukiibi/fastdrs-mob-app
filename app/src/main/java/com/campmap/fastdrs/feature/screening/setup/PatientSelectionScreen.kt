package com.campmap.fastdrs.feature.screening.setup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.domain.model.Patient

@Composable
fun PatientSelectionScreen(
    patients: List<Patient>,
    onPatientSelected: (Patient) -> Unit,
    onCreateNewPatient: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Select Patient", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onCreateNewPatient, modifier = Modifier.fillMaxWidth()) {
            Text("Create New Patient")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(patients) { patient ->
                ListItem(
                    headlineContent = { Text("Patient ID: ${patient.id}") },
                    supportingContent = { Text("Age: ${patient.age}, Sex: ${patient.sex}") },
                    trailingContent = {
                        Button(onClick = { onPatientSelected(patient) }) { Text("Select") }
                    }
                )
            }
        }
    }
}
