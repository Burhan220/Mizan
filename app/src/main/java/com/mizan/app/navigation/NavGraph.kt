package com.mizan.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mizan.app.ui.screens.DashboardScreen
import com.mizan.app.ui.screens.DebtsScreen
import com.mizan.app.ui.screens.ExpensesScreen
import com.mizan.app.ui.screens.SalaryScreen

@Composable
fun MizanNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Dashboard,
        modifier = modifier
    ) {
        composable(Destinations.Dashboard) {
            DashboardScreen(onNavigate = { route -> navController.navigate(route) })
        }
        composable(Destinations.Debts) {
            DebtsScreen(onNavigate = { route -> navController.navigate(route) })
        }
        composable(Destinations.Expenses) {
            ExpensesScreen(onNavigate = { route -> navController.navigate(route) })
        }
        composable(Destinations.Salary) {
            SalaryScreen(onNavigate = { route -> navController.navigate(route) })
        }
    }
}
