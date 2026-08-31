package com.campmap.fastdrs.feature.screening.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.campmap.fastdrs.core.image.FundusImage
import com.campmap.fastdrs.ui.components.ClinicalCard
import com.campmap.fastdrs.ui.theme.MedicalBlue

@Composable
fun ImageReviewScreen(image: FundusImage, onRetake: () -> Unit, onAnalyze: () -> Unit) {
    val context = LocalContext.current
    val bitmap = remember(image) { image.toBitmap(context) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Review Image",
            style = MaterialTheme.typography.headlineMedium,
            color = MedicalBlue,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        if (bitmap != null) {
            ClinicalCard(modifier = Modifier.weight(1f)) {
                Image(
                    painter = rememberAsyncImagePainter(bitmap),
                    contentDescription = "Fundus Image",
                    modifier = Modifier.fillMaxSize().padding(8.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(onClick = onRetake, modifier = Modifier.weight(1f)) { 
                Text("Retake") 
            }
            Button(onClick = onAnalyze, modifier = Modifier.weight(1f)) { 
                Text("Analyze Image") 
            }
        }
    }
}
