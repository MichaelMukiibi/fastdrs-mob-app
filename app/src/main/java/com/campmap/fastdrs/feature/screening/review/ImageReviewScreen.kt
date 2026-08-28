package com.campmap.fastdrs.feature.screening.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.campmap.fastdrs.core.image.FundusImage

@Composable
fun ImageReviewScreen(image: FundusImage, onRetake: () -> Unit, onAnalyze: () -> Unit) {
    val context = LocalContext.current
    val bitmap = remember(image) { image.toBitmap(context) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (bitmap != null) {
            Image(
                painter = rememberAsyncImagePainter(bitmap),
                contentDescription = "Fundus Image",
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = onRetake) { Text("Retake") }
            Button(onClick = onAnalyze) { Text("Analyze") }
        }
    }
}
