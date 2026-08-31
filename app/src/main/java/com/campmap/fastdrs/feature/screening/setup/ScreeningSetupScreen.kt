package com.campmap.fastdrs.feature.screening.setup

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.domain.model.Eye
import com.campmap.fastdrs.ui.components.ClinicalCard
import com.campmap.fastdrs.ui.theme.MedicalBlue

@Composable
fun ScreeningSetupScreen(onContinue: (Eye) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Select Eye", 
            style = MaterialTheme.typography.headlineMedium,
            color = MedicalBlue,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        ClinicalCard {
            Column(modifier = Modifier.padding(16.dp)) {
                Button(
                    onClick = { onContinue(Eye.LEFT) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Left Eye")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onContinue(Eye.RIGHT) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Right Eye")
                }
            }
        }
    }
}
