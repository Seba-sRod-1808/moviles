package plat.sebastian.lab7.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import plat.sebastian.lab7.screens.CharacterDetailScreen
import plat.sebastian.lab7.screens.CharactersScreen
import plat.sebastian.lab7.screens.LoginScreen

@Serializable
object LoginDestination

@Serializable
object CharactersDestination

@Serializable
data class CharacterDetailDestination(
    val id: Int
)

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

                    navController.navigate(CharactersDestination) {

                        popUpTo(LoginDestination) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<CharactersDestination> {

            CharactersScreen(
                onCharacterClick = { characterId ->

                    navController.navigate(
                        CharacterDetailDestination(
                            id = characterId
                        )
                    )
                }
            )
        }

        composable<CharacterDetailDestination> { backStackEntry ->

            val destination =
                backStackEntry.toRoute<CharacterDetailDestination>()

            CharacterDetailScreen(
                characterId = destination.id,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}