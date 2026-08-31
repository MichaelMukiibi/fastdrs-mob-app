import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.campmap.fastdrs.domain.model.Patient
import com.campmap.fastdrs.ui.components.ClinicalCard
import java.util.UUID

@Composable
fun NewPatientScreen(onPatientCreated: (Patient) -> Unit) {
    var age by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Create New Patient", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        ClinicalCard {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = sex,
                    onValueChange = { sex = it },
                    label = { Text("Sex") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val patient = Patient(id = UUID.randomUUID().toString(), age = age.toIntOrNull() ?: 0, sex = sex)
                        onPatientCreated(patient)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Create Patient")
                }
            }
        }
    }
}
