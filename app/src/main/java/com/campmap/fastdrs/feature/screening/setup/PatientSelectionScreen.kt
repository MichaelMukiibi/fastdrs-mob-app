package com.campmap.fastdrs.feature.screening.setup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.domain.model.Patient
import com.campmap.fastdrs.ui.components.ClinicalCard

@Composable
fun PatientSelectionScreen(
    patients: List<Patient>,
    onPatientSelected: (Patient) -> Unit,
    onCreateNewPatient: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Select Patient", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onCreateNewPatient, modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
            Text("Create New Patient")
        }
        Text("Existing Patients", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn {
            items(patients) { patient ->
                ClinicalCard(modifier = Modifier.padding(vertical = 4.dp)) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = androidx.compose.ui.graphics.Color.Transparent),
                        headlineContent = { Text("ID: ${patient.id.take(8)}", fontWeight = FontWeight.Bold) },
                        supportingContent = { Text("Age: ${patient.age} | Sex: ${patient.sex}") },
                        trailingContent = {
                            Button(onClick = { onPatientSelected(patient) }) { Text("Select") }
                        }
                    )
                }
            }
        }
    }
}
