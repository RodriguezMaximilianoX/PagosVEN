package com.rmxdev.pagosven.presenter.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rmxdev.pagosven.R
import com.rmxdev.pagosven.ui.theme.Black
import com.rmxdev.pagosven.ui.theme.Blue
import com.rmxdev.pagosven.ui.theme.White

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToTransfer: () -> Unit,
    navigateToDeposit: () -> Unit,
    navigateToCharge: () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.isStatusBarVisible = false
        systemUiController.isNavigationBarVisible = false
    }

    val userName by viewModel.userName.collectAsState()
    val userBalance by viewModel.userBalance.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.bk_register),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Hola, $userName", color = Blue, fontSize = 20.sp)
                    Icon(
                        painterResource(id = R.drawable.ic_account),
                        contentDescription = "Go to account",
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { navigateToDeposit() },
                        colors = buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .border(2.dp, Blue, CircleShape)
                            .weight(1f)
                    ) {
                        Text(text = "Depositar", color = Blue, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.weight(0.10f))
                    Button(
                        onClick = { navigateToTransfer() },
                        colors = buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .border(2.dp, Blue, CircleShape)
                            .weight(1f)
                    ) {
                        Text(text = "Transferir", color = Blue, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.weight(0.10f))
                    Button(
                        onClick = { navigateToCharge() },
                        colors = buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .border(2.dp, Blue, CircleShape)
                            .weight(1f)
                    ) {
                        Text(text = "Cobrar", color = Blue, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.25f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Su saldo es: ",
                        fontSize = 30.sp,
                        color = Blue,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$userBalance",
                        fontSize = 30.sp,
                        color = Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.25f))
            Image(
                painterResource(id = R.drawable.home_first_app_pay),
                contentDescription = "App logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .width(400.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
                containerColor = White,
                modifier = Modifier.size(75.dp)
            ) {
                Text(text = "QR", color = Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}