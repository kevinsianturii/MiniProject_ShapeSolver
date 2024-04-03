package org.d3if0055.assessment1app.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Persegi : Screen("persegi")
    data object PersegiPanjang : Screen("persegi_panjang")
    data object Lingkaran : Screen("lingkaran")
    data object Segitiga : Screen("segitiga")
    data object About : Screen("about")
}