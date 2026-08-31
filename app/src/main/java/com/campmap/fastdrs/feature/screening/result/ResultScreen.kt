import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.core.ml.Prediction
import com.campmap.fastdrs.ui.components.ClinicalCard
import com.campmap.fastdrs.ui.theme.MedicalBlue

@Composable
fun ResultScreen(prediction: Prediction, onDone: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Screening result", style = MaterialTheme.typography.headlineMedium, color = MedicalBlue, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        
        ClinicalCard {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(prediction.predictedClass.name.replace("_", " "), style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold)
                Text("Confidence: ${(prediction.confidence * 100).toInt()}%", style = MaterialTheme.typography.bodyLarge)
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        Text("Class Probabilities", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(prediction.probabilities.toList()) { (clazz, prob) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(clazz.name.replace("_", " "), style = MaterialTheme.typography.bodyMedium)
                    Text("${(prob * 100).toInt()}%", fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Professional examination is recommended.", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onDone, modifier = Modifier.fillMaxWidth()) {
            Text("Done")
        }
    }
}
