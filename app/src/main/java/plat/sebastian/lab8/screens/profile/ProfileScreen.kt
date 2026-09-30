package plat.sebastian.lab8.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Perfil",
            modifier = Modifier.size(220.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        ProfileInfoRow(
            label = "Nombre:",
            value = "Sebastian Rodas"
        )

        ProfileInfoRow(
            label = "Carné:",
            value = "25038"
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        OutlinedButton(
            onClick = onLogout
        ) {
            Text("Cerrar sesión")
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ProfileInfoRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge
        )
    }
}