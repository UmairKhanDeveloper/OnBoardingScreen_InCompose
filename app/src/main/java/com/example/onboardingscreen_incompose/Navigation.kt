package com.example.onboardingscreen_incompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screens.OnboardingScreen.route) {
            OnboardingScreen(navController)
        }
        composable(Screens.MainScreen.route){
            MainScreen(navController)
        }
    }
}


sealed class Screens(val route: String, val title: String) {
    object SplashScreen : Screens("SplashScreen", "SplashScreen")
    object MainScreen : Screens("MainScreen", "MainScreen")
    object OnboardingScreen : Screens("OnboardingScreen", "OnboardingScreen")
}


@Composable
fun App() {

    val navController = rememberNavController()
    Navigation(navController = navController)
}