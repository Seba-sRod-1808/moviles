package plat.sebastian.lab9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import plat.sebastian.lab9.navigation.AppNavigation
import plat.sebastian.lab9.ui.theme.Lab9Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab9Theme {
                AppNavigation()
            }
        }
    }
}