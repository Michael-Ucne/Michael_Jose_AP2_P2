package com.phantomshard.michael_jose_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.phantomshard.michael_jose_ap2_p2.presentation.detail.JugadorDetailScreen
import com.phantomshard.michael_jose_ap2_p2.presentation.list.JugadorListScreen

@Composable
fun NavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.JugadorList
    ) {
        composable<Screen.JugadorList> {
            JugadorListScreen(
                onAddJugador = {
                    navHostController.navigate(Screen.JugadorDetail(id = 0))
                },
                onEditJugador = { id ->
                    navHostController.navigate(Screen.JugadorDetail(id = id))
                }
            )
        }
        composable<Screen.JugadorDetail> { backStackEntry ->
            val detailRoute: Screen.JugadorDetail = backStackEntry.toRoute()
            JugadorDetailScreen(
                id = detailRoute.id,
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}
