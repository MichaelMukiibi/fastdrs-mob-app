package com.campmap.fastdrs.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.ui.components.ClinicalCard
import com.campmap.fastdrs.ui.theme.MedicalBlue

@Composable
fun HomeScreen(onStartScreening: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "fastDRS",
            style = MaterialTheme.typography.headlineLarge,
            color = MedicalBlue,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(48.dp))
        ClinicalCard {
            Button(
                onClick = onStartScreening,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Start New Screening")
            }
        }
    }
}
