package com.rmxdev.pagosven.presenter.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmxdev.pagosven.ui.theme.Black
import com.rmxdev.pagosven.ui.theme.Blue
import com.rmxdev.pagosven.ui.theme.OffWhite
import com.rmxdev.pagosven.ui.theme.White

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToTransfer: () -> Unit,
    navigateToDeposit: () -> Unit,
    navigateToCharge: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(text = "Bienvenido", fontSize = 35.sp, color = Black)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(1.dp)
                .background(
                    OffWhite
                )
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "Su saldo actual es de:",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "$20000", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(1.dp)
                .background(
                    OffWhite
                )
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Button(
            onClick = { navigateToTransfer() },
            colors = buttonColors(containerColor = Blue, contentColor = White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(text = "Transferencia", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
        Button(
            onClick = { navigateToDeposit() },
            colors = buttonColors(containerColor = Blue, contentColor = White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(text = "Ingresar dinero", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
        Button(
            onClick = { navigateToCharge() },
            colors = buttonColors(containerColor = Blue, contentColor = White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(text = "Cobrar con QR", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.weight(2f))
        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = Blue,
            contentColor = White
        ) {
            Text(text = "QR", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.weight(1f))


    }
}