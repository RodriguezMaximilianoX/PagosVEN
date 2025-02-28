package com.rmxdev.pagosven.presenter.pay

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rmxdev.pagosven.R
import com.rmxdev.pagosven.ui.theme.Black
import com.rmxdev.pagosven.ui.theme.Blue
import com.rmxdev.pagosven.ui.theme.White

@Composable
fun PayScreen(
    modifier: Modifier = Modifier,
    viewModel: PayViewModel = viewModel(),
    payAmount: String,
    userId: String,
    navigateToHome: () -> Unit
) {
    val payState by viewModel.payState.collectAsState()
    val context = LocalContext.current

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
            Text(
                text = "Monto a pagar",
                fontSize = 35.sp,
                color = Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(White)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            )
            Text(
                payAmount,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(White)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Button(
                onClick = { viewModel.pay(userId, payAmount.toDouble()) },
                colors = buttonColors(containerColor = Blue, contentColor = White),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "Pagar",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
            }
        }
        when(payState) {
            is PayState.Loading -> {
                CircularProgressIndicator()
            }
            is PayState.Success -> {
                LaunchedEffect(payState) {
                    Toast.makeText(context, "Pago realizado con éxito", Toast.LENGTH_LONG).show()
                    navigateToHome()
                }
            }
            is PayState.Error -> {
                LaunchedEffect(payState) {
                    Toast.makeText(context, "Error al realizar el pago", Toast.LENGTH_LONG).show()
                }
            }
            else -> {}
        }
    }


}