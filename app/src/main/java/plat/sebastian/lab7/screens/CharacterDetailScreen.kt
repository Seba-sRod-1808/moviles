package plat.sebastian.lab7.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import plat.sebastian.lab7.CharacterDb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    onBackClick: () -> Unit
) {

    val character =
        CharacterDb().getCharacterById(characterId)

    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text("Characters details")
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(220.dp)
                    .clip(CircleShape)
            )

            Text(
                text = character.name,
                modifier = Modifier.padding(
                    top = 20.dp,
                    bottom = 30.dp
                ),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            CharacterInfoRow(
                label = "Species:",
                value = character.species
            )

            CharacterInfoRow(
                label = "Status:",
                value = character.status
            )

            CharacterInfoRow(
                label = "Gender:",
                value = character.gender
            )
        }
    }
}

@Composable
fun CharacterInfoRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}