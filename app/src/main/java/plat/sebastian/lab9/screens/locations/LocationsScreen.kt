package plat.sebastian.lab9.screens.locations

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import plat.sebastian.lab9.Location
import plat.sebastian.lab9.components.ErrorLayout
import plat.sebastian.lab9.components.LoadingLayout
import plat.sebastian.lab9.viewmodel.LocationsViewModel

@Composable
fun LocationsScreen(
    onLocationClick: (Int) -> Unit,
    viewModel: LocationsViewModel = viewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    when {
        state.hasError -> ErrorLayout(
            onRetry = viewModel::loadLocations
        )

        state.isLoading -> LoadingLayout(
            onClick = viewModel::showError
        )

        state.data != null -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = state.data,
                    key = { it.id }
                ) { location ->
                    LocationItem(
                        location = location,
                        onClick = {
                            onLocationClick(location.id)
                        }
                    )
                }
            }
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
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 20.dp)
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