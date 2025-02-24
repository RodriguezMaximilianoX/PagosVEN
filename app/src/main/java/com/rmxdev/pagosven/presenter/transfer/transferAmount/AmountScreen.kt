package com.rmxdev.pagosven.presenter.transfer.transferAmount

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmxdev.pagosven.R
import com.rmxdev.pagosven.domain.entities.User
import com.rmxdev.pagosven.ui.theme.Black
import com.rmxdev.pagosven.ui.theme.Blue
import com.rmxdev.pagosven.ui.theme.White

@Composable
fun AmountScreen(
    modifier: Modifier = Modifier,
    viewModel: AmountViewModel = hiltViewModel(),
    userReceiver: User,
    navigateToHome: () -> Unit
) {

    var amount by rememberSaveable { mutableStateOf("") }
    val amountState by viewModel.amountState.collectAsState()
    val context = LocalContext.current
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
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Saldo Actual", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Blue)
                Text(
                    "$userBalance",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
            }
            Spacer(modifier = Modifier.weight(0.25f))
            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Ingresa la cantidad a transferir", fontWeight = FontWeight.Bold) },
                modifier = Modifier
                    .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .clip(CircleShape),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = White,
                    focusedContainerColor = White,
                    focusedTextColor = Black,
                    unfocusedTextColor = Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                maxLines = 1
            )
            Button(
                onClick = { viewModel.transferMoney(userReceiver, amount.toDouble()) },
                colors = buttonColors(containerColor = Blue, contentColor = White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                enabled = amount.isNotBlank()
            ) {
                Text(
                    text = "Enviar dinero",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            when (amountState) {
                is AmountState.Loading -> {
                    CircularProgressIndicator()
                }

                is AmountState.Success -> {
                    LaunchedEffect(amountState) {
                        Toast.makeText(
                            context, "Transferencia exitosa", Toast.LENGTH_LONG
                        ).show()
                        navigateToHome()
                    }
                }

                is AmountState.Error -> {
                    LaunchedEffect(amountState) {
                        Toast.makeText(
                            context, "Error al transferir dinero", Toast.LENGTH_LONG
                        ).show()
                    }
                    Log.d("AmountScreen", "Error: ${(amountState as AmountState.Error).message}")
                }

                else -> {}
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}