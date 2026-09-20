package plat.sebastian.lab7.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import plat.sebastian.lab7.R

@Composable
fun LoginScreen(
    onStartClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Image(
            painter = painterResource(
                id = R.drawable.rick_morty_logo
            ),
            contentDescription = "Rick and Morty",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Button(
            onClick = onStartClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Empezar")
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "Sebastian Rodas - 25038",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}