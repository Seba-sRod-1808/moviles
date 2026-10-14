package plat.sebastian.lab9.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import plat.sebastian.lab9.screens.login.LoginScreen
import plat.sebastian.lab9.screens.main.MainScreen
import plat.sebastian.lab9.viewmodel.SessionViewModel

@Serializable
object SplashDestination

@Serializable
object LoginDestination

@Serializable
object MainDestination

@Composable
fun AppNavigation(
    sessionViewModel: SessionViewModel = viewModel()
) {
    val navController = rememberNavController()

    val sessionState =
        sessionViewModel.uiState.collectAsStateWithLifecycle().value

    NavHost(
        navController = navController,
        startDestination = SplashDestination
    ) {
        composable<SplashDestination> {

            LaunchedEffect(
                sessionState.isChecking,
                sessionState.userName
            ) {
                if (!sessionState.isChecking) {
                    val destination =
                        if (sessionState.userName != null) {
                            MainDestination
                        } else {
                            LoginDestination
                        }

                    navController.navigate(destination) {
                        popUpTo<SplashDestination> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()

                Text(
                    text = "Verificando sesión...",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        composable<LoginDestination> {

            LoginScreen(
                isSaving = sessionState.isSaving,
                hasError = sessionState.hasError,
                onStartClick = { name ->
                    sessionViewModel.login(name) {
                        navController.navigate(MainDestination) {
                            popUpTo<LoginDestination> {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }

        composable<MainDestination> {

            MainScreen(
                userName = sessionState.userName.orEmpty(),
                onLogout = {
                    sessionViewModel.logout {
                        navController.navigate(LoginDestination) {
                            popUpTo<MainDestination> {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}