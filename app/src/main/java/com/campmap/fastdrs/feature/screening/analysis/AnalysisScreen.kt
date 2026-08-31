import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.ui.theme.MedicalBlue

@Composable
fun AnalysisScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = MedicalBlue, strokeWidth = 4.dp)
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            "Analyzing image...", 
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Running clinical model on-device", 
            style = MaterialTheme.typography.bodyMedium,
            color = androidx.compose.ui.graphics.Color.Gray
        )
    }
}
