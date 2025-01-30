package com.rmxdev.pagosven.presenter.initial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmxdev.pagosven.ui.theme.Black
import com.rmxdev.pagosven.ui.theme.Blue
import com.rmxdev.pagosven.ui.theme.White
import com.rmxdev.pagosven.ui.theme.Yellow

@Composable
fun InitialScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = "PagosVEN",
            fontSize = 50.sp,
            color = Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 36.dp)
        )
        Text(text = "Inicia sesión o regístrate", fontSize = 20.sp, color = Black)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { navigateToLogin() },
            colors = buttonColors(containerColor = Yellow),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Iniciar sesión", color = Black, fontSize = 30.sp)
        }
        Button(
            onClick = { navigateToSignUp() },
            colors = buttonColors(containerColor = Yellow),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Registrarse", color = Blue, fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}