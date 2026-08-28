package com.campmap.fastdrs.feature.screening.acquisition

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.core.image.FundusImage

@Composable
fun ImageAcquisitionScreen(onImageSelected: (FundusImage) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { onImageSelected(FundusImage(it.toString())) }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Select Fundus Image")
        }
    }
}
