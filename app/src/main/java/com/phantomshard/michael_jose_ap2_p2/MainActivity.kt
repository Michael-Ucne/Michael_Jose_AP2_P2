package com.phantomshard.michael_jose_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.phantomshard.michael_jose_ap2_p2.presentation.navigation.NavHost
import com.phantomshard.michael_jose_ap2_p2.ui.theme.Michael_Jose_AP2_P2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Michael_Jose_AP2_P2Theme {
                val navController = rememberNavController()
                NavHost(navHostController = navController)
            }
        }
    }
}