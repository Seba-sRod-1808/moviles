package plat.sebastian.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import plat.sebastian.lab8.navigation.AppNavigation
import plat.sebastian.lab8.ui.theme.Lab8Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab8Theme {
                AppNavigation()
            }
        }
    }
}