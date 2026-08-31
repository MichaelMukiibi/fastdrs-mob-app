package com.campmap.fastdrs.feature.screening.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.domain.model.Screening
import com.campmap.fastdrs.ui.components.ClinicalCard
import com.campmap.fastdrs.ui.theme.MedicalBlue

@Composable
fun HistoryScreen(screenings: List<Screening>) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text(
            "Screening History", 
            style = MaterialTheme.typography.headlineMedium,
            color = MedicalBlue,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(screenings) { screening ->
                ClinicalCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Eye: ${screening.eye.name}", 
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Date: ${java.text.SimpleDateFormat("dd MMM yyyy, HH:mm", java.util.Locale.getDefault()).format(java.util.Date(screening.timestamp))}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Result: ${screening.prediction?.take(20) ?: "Pending"}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MedicalBlue
                        )
                    }
                }
            }
        }
    }
}
