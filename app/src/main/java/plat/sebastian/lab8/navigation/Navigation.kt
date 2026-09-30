package plat.sebastian.lab8.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import plat.sebastian.lab8.screens.login.LoginScreen
import plat.sebastian.lab8.screens.main.MainScreen

@Serializable
object LoginDestination

@Serializable
object MainDestination

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginDestination
    ) {

        composable<LoginDestination> {

            LoginScreen(
                onStartClick = {

                    navController.navigate(MainDestination) {

                        popUpTo<LoginDestination> {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<MainDestination> {

            MainScreen(
                onLogout = {

                    navController.navigate(LoginDestination) {

                        popUpTo<MainDestination> {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}