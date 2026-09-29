package plat.sebastian.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import plat.sebastian.lab7.navigation.AppNavigation
import plat.sebastian.lab7.ui.theme.Lab7Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab7Theme {
                AppNavigation()
            }
        }
    }
}