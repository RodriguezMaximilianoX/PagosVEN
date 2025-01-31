package com.rmxdev.pagosven.presenter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rmxdev.pagosven.presenter.charge.ChargeScreen
import com.rmxdev.pagosven.presenter.deposit.DepositScreen
import com.rmxdev.pagosven.presenter.home.HomeScreen
import com.rmxdev.pagosven.presenter.initial.InitialScreen
import com.rmxdev.pagosven.presenter.login.LoginScreen
import com.rmxdev.pagosven.presenter.signup.SignupScreen
import com.rmxdev.pagosven.presenter.transfer.TransferScreen

@Composable
fun NavigationWrapper(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier){
        composable("initial"){
           InitialScreen(
               modifier = modifier,
               navigateToLogin = { navController.navigate("login") },
               navigateToSignUp = { navController.navigate("signup") }
           )
        }
        composable("login"){
            LoginScreen(
                modifier = modifier,
                navigateToHome = { navController.navigate("home") }
            )
        }
        composable("signup"){
            SignupScreen(
                modifier = modifier,
                navigateToHome = { navController.navigate("home") }
            )
        }
        composable("home"){
            HomeScreen(
                modifier = modifier,
                navigateToTransfer = { navController.navigate("transfer") },
                navigateToDeposit = { navController.navigate("deposit") },
                navigateToCharge = { navController.navigate("charge") }
            )
        }
        composable("transfer"){
            TransferScreen(
                modifier = modifier
            )
        }
        composable("deposit"){
            DepositScreen(
                modifier = modifier
            )
        }
        composable("charge"){
            ChargeScreen(
                modifier = modifier
            )
        }

    }
}