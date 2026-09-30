package plat.sebastian.lab8.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import plat.sebastian.lab8.screens.characters.CharacterDetailScreen
import plat.sebastian.lab8.screens.characters.CharactersScreen
import plat.sebastian.lab8.screens.locations.LocationDetailScreen
import plat.sebastian.lab8.screens.locations.LocationsScreen
import plat.sebastian.lab8.screens.profile.ProfileScreen

@Serializable
object CharactersGraph

@Serializable
object CharactersDestination

@Serializable
data class CharacterDetailDestination(
    val id: Int
)

@Serializable
object LocationsGraph

@Serializable
object LocationsDestination

@Serializable
data class LocationDetailDestination(
    val id: Int
)

@Serializable
object ProfileDestination

@Composable
fun MainScreen(
    onLogout: () -> Unit
) {
    val navController = rememberNavController()

    val backStackEntry =
        navController.currentBackStackEntryAsState().value

    val currentDestination =
        backStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected =
                        currentDestination?.hierarchy?.any {
                            it.hasRoute<CharactersGraph>()
                        } == true,
                    onClick = {
                        navController.navigate(CharactersGraph) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = "Characters"
                        )
                    },
                    label = {
                        Text("Characters")
                    }
                )

                NavigationBarItem(
                    selected =
                        currentDestination?.hierarchy?.any {
                            it.hasRoute<LocationsGraph>()
                        } == true,
                    onClick = {
                        navController.navigate(LocationsGraph) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Locations"
                        )
                    },
                    label = {
                        Text("Locations")
                    }
                )

                NavigationBarItem(
                    selected =
                        currentDestination
                            ?.hasRoute<ProfileDestination>() == true,
                    onClick = {
                        navController.navigate(ProfileDestination) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    },
                    label = {
                        Text("Profile")
                    }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = CharactersGraph,
            modifier = Modifier.padding(innerPadding)
        ) {

            navigation<CharactersGraph>(
                startDestination = CharactersDestination
            ) {

                composable<CharactersDestination> {

                    CharactersScreen(
                        onCharacterClick = { id ->
                            navController.navigate(
                                CharacterDetailDestination(
                                    id = id
                                )
                            )
                        }
                    )
                }

                composable<CharacterDetailDestination> { backStackEntry ->

                    val destination =
                        backStackEntry
                            .toRoute<CharacterDetailDestination>()

                    CharacterDetailScreen(
                        characterId = destination.id,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }

            navigation<LocationsGraph>(
                startDestination = LocationsDestination
            ) {

                composable<LocationsDestination> {

                    LocationsScreen(
                        onLocationClick = { id ->
                            navController.navigate(
                                LocationDetailDestination(
                                    id = id
                                )
                            )
                        }
                    )
                }

                composable<LocationDetailDestination> { backStackEntry ->

                    val destination =
                        backStackEntry
                            .toRoute<LocationDetailDestination>()

                    LocationDetailScreen(
                        locationId = destination.id,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }

            composable<ProfileDestination> {

                ProfileScreen(
                    onLogout = onLogout
                )
            }
        }
    }
}