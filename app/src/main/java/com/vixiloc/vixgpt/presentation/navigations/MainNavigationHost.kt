package com.vixiloc.vixgpt.presentation.navigations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vixiloc.vixgpt.presentation.home.HomeScreen
import com.vixiloc.vixgpt.presentation.settings.SettingsScreen

@Composable
fun MainNavigationHost(navhostController: NavHostController) {
    NavHost(navController = navhostController, startDestination = MainRoute.Home.name) {
        composable(
            route = MainRoute.Home.name,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                    animationSpec = tween(300)
                )
            },
        ) {
            HomeScreen(navhostController = navhostController)
        }
        composable(
            route = MainRoute.Settings.name,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                    animationSpec = tween(300)
                )
            },
        ) {
            SettingsScreen(navhostController = navhostController)
        }
    }
}