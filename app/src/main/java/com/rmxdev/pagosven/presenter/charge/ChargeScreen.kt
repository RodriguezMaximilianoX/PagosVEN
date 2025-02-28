package com.rmxdev.pagosven.presenter.charge

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmxdev.pagosven.R
import com.rmxdev.pagosven.domain.entities.User
import com.rmxdev.pagosven.ui.theme.Black
import com.rmxdev.pagosven.ui.theme.Blue
import com.rmxdev.pagosven.ui.theme.White

@Composable
fun ChargeScreen(
    modifier: Modifier = Modifier,
    userId: String,
    navigateToQrScreen: (String, String) -> Unit
) {

    var amount by rememberSaveable { mutableStateOf("") }

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
        ){
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "Cobrar",
                fontSize = 35.sp,
                color = Blue,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(White)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 54.dp, vertical = 16.dp)
                    .height(3.dp)
                    .background(
                        Black
                    )
            )
            Spacer(modifier = Modifier.weight(0.30f))
            Text(
                text = "Total a cobrar",
                fontSize = 30.sp,
                color = Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.weight(0.1f))
            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Ingresa el monto a cobrar", fontWeight = FontWeight.Bold) },
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
                onClick = { navigateToQrScreen(userId, amount) },
                colors = buttonColors(containerColor = Blue, contentColor = White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                enabled = amount.isNotBlank()
            ) {
                Text(
                    text = "Generar código QR",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}