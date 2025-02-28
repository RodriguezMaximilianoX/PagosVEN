package com.rmxdev.pagosven.presenter

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.rmxdev.pagosven.presenter.charge.ChargeScreen
import com.rmxdev.pagosven.presenter.home.HomeScreen
import com.rmxdev.pagosven.presenter.initial.InitialScreen
import com.rmxdev.pagosven.presenter.login.LoginScreen
import com.rmxdev.pagosven.presenter.pay.PayScreen
import com.rmxdev.pagosven.presenter.registers.RegistersScreen
import com.rmxdev.pagosven.presenter.signup.SignupScreen
import com.rmxdev.pagosven.presenter.transfer.transferAmount.AmountScreen
import com.rmxdev.pagosven.presenter.transfer.transferFindUser.TransferScreen

@Composable
fun NavigationWrapper(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable("initial") {
            InitialScreen(
                modifier = modifier,
                navigateToLogin = { navController.navigate("login") },
                navigateToSignUp = { navController.navigate("signup") }
            )
        }
        composable("login") {
            LoginScreen(
                modifier = modifier,
                navigateToHome = { navController.navigate("home") }
            )
        }
        composable("signup") {
            SignupScreen(
                modifier = modifier,
                navigateToHome = { navController.navigate("home") }
            )
        }
        composable("home") {
            HomeScreen(
                modifier = modifier,
                navigateToTransfer = { navController.navigate("transfer") },
                navigateToRegisters = { navController.navigate("registers") },
                navigateToCharge = { navController.navigate("charge") },
                navigateToProfile = { navController.navigate("profile") },
                navigateToPay = { payAmount ->
                    navController.navigate("pay/$payAmount")
                }
            )
        }
        composable("transfer") {
            TransferScreen(
                modifier = modifier,
                navigateToAmount = {
                    val userJson = Uri.encode(Gson().toJson(it))
                    navController.navigate("amount/$userJson")
                }
            )
        }
        composable("amount/{userJson}") {
            val userJson = it.arguments?.getString("userJson")
            val userReceiver =
                Gson().fromJson(userJson, com.rmxdev.pagosven.domain.entities.User::class.java)
            AmountScreen(
                modifier = modifier,
                userReceiver = userReceiver,
                navigateToHome = { navController.navigate("home") }
            )
        }
        composable("registers") {
            RegistersScreen(
                modifier = modifier
            )
        }
        composable("charge") {
            ChargeScreen(
                modifier = modifier
            )
        }
        composable("pay/{payAmount}") {
            val payAmount = it.arguments?.getString("payAmount")
            PayScreen(
                modifier = modifier,
                payAmount = payAmount ?: ""
            )
        }

    }
}