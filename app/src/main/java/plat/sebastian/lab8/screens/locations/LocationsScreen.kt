package plat.sebastian.lab8.screens.locations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import plat.sebastian.lab8.Location
import plat.sebastian.lab8.LocationDb

@Composable
fun LocationsScreen(
    onLocationClick: (Int) -> Unit
) {

    val locations =
        LocationDb().getAllLocations()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(locations) { location ->

            LocationItem(
                location = location,
                onClick = {
                    onLocationClick(location.id)
                }
            )
        }
    }
}

@Composable
fun LocationItem(
    location: Location,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 24.dp,
                vertical = 20.dp
            )
    ) {

        Text(
            text = location.name,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = location.type,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}