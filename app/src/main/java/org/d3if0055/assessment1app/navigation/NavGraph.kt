package org.d3if0055.assessment1app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if0055.assessment1app.ui.screen.AboutScreen
import org.d3if0055.assessment1app.ui.screen.LingkaranScreen
import org.d3if0055.assessment1app.ui.screen.MainScreen
import org.d3if0055.assessment1app.ui.screen.MainViewModel
import org.d3if0055.assessment1app.ui.screen.PersegiPanjangScreen
import org.d3if0055.assessment1app.ui.screen.PersegiScreen
import org.d3if0055.assessment1app.ui.screen.SegitigaScreen


@Composable
fun SetUpNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController, mainViewModel = MainViewModel() )
        }

        composable(route = Screen.Persegi.route) {
            PersegiScreen(navController)
        }

        composable(route = Screen.PersegiPanjang.route) {
            PersegiPanjangScreen(navController)
        }

        composable(route = Screen.Lingkaran.route) {
            LingkaranScreen(navController)
        }

        composable(route = Screen.Segitiga.route) {
            SegitigaScreen(navController)
        }

        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
    }
}




