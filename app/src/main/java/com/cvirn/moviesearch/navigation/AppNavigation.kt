package com.cvirn.moviesearch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cvirn.moviesearch.ui.screen.fllter.FilterContent
import com.cvirn.moviesearch.ui.screen.home.HomeContent
import com.cvirn.moviesearch.viewmodel.SharedViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun AppNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
    NavHost(navController = navController, startDestination = NavigationRoute.HOME_SCREEN) {
        composable(NavigationRoute.HOME_SCREEN) {
            HomeContent(
                sharedViewModel,
                goFilterContent = { navController.navigate(NavigationRoute.FILTER_SCREEN) },
            )
        }
        composable(NavigationRoute.FILTER_SCREEN) {
            FilterContent(sharedViewModel)
        }
    }
}

object NavigationRoute {
    const val HOME_SCREEN: String = "home_screen"
    const val FILTER_SCREEN: String = "filter_screen"
}
