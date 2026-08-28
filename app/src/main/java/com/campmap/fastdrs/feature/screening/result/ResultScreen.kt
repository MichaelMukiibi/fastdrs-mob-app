package com.campmap.fastdrs.feature.screening.result

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.core.ml.Prediction

@Composable
fun ResultScreen(prediction: Prediction, onDone: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Screening result", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(prediction.predictedClass.name, style = MaterialTheme.typography.headlineMedium)
        Text("Confidence: ${(prediction.confidence * 100).toInt()}%")
        Spacer(modifier = Modifier.height(16.dp))
        prediction.probabilities.forEach { (clazz, prob) ->
            Text("${clazz.name}: ${(prob * 100).toInt()}%")
        }
        Spacer(modifier = Modifier.weight(1f))
        Text("Professional examination is recommended.", style = MaterialTheme.typography.bodySmall)
        Button(onClick = onDone, modifier = Modifier.fillMaxWidth()) {
            Text("Done")
        }
    }
}
