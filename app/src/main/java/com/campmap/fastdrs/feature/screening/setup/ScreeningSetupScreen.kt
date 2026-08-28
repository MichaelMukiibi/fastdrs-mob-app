package com.campmap.fastdrs.feature.screening.setup

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.domain.model.Eye

@Composable
fun ScreeningSetupScreen(onContinue: (Eye) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Select Eye", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onContinue(Eye.LEFT) }) {
            Text("Left Eye")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { onContinue(Eye.RIGHT) }) {
            Text("Right Eye")
        }
    }
}
