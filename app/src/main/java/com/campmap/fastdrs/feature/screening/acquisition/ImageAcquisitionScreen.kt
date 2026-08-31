package com.campmap.fastdrs.feature.screening.acquisition

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.core.image.FundusImage
import com.campmap.fastdrs.ui.components.ClinicalCard
import com.campmap.fastdrs.ui.theme.MedicalBlue

@Composable
fun ImageAcquisitionScreen(onImageSelected: (FundusImage) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { onImageSelected(FundusImage(it.toString())) }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Acquire Fundus Image",
            style = MaterialTheme.typography.headlineMedium,
            color = MedicalBlue,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        ClinicalCard {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Select from Gallery")
                }
            }
        }
    }
}
