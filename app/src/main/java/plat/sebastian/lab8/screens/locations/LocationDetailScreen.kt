package plat.sebastian.lab8.screens.locations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import plat.sebastian.lab8.LocationDb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(
    locationId: Int,
    onBackClick: () -> Unit
) {

    val location =
        LocationDb().getLocationById(locationId)

    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text("Location details")
                },

                navigationIcon = {

                    IconButton(
                        onClick = onBackClick
                    ) {
                        Text(
                            text = "←",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =
                        MaterialTheme.colorScheme.primaryContainer,

                    titleContentColor =
                        MaterialTheme.colorScheme.onPrimaryContainer,

                    navigationIconContentColor =
                        MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(32.dp)
        ) {

            Text(
                text = location.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 30.dp,
                        bottom = 40.dp
                    ),
                style = MaterialTheme.typography.headlineLarge
            )

            LocationInfoRow(
                label = "ID:",
                value = location.id.toString()
            )

            LocationInfoRow(
                label = "Type:",
                value = location.type
            )

            LocationInfoRow(
                label = "Dimension:",
                value = location.dimension
            )
        }
    }
}

@Composable
fun LocationInfoRow(
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
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium
        )
    }
}