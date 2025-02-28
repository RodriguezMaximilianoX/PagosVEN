package com.rmxdev.pagosven.presenter.qr

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.rmxdev.pagosven.R
import com.rmxdev.pagosven.domain.entities.User
import com.rmxdev.pagosven.ui.theme.Blue
import com.rmxdev.pagosven.ui.theme.White

@Composable
fun QrScreen(
    modifier: Modifier = Modifier,
    viewModel: QrViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    qrAmount: String,
    userId: String

    ) {
    val qrState by viewModel.qrState.collectAsState()
    val qrCodeBitmap = viewModel.qrCodeBitmap.collectAsState().value

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
            qrCodeBitmap?.let { bitmap ->
                val painter = rememberAsyncImagePainter(model = bitmap)
                Image(
                    painter = painter,
                    contentDescription = "QR Code",
                    modifier = Modifier.size(250.dp)
                )
            }
            Spacer(modifier = Modifier.weight(0.25f))
            Button(
                onClick = { navigateToHome() },
                colors = buttonColors(containerColor = Blue, contentColor = White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "Volver al inicio",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }

    when (qrState) {
        is QrState.Initial -> {
            viewModel.qrCode(qrAmount, userId)
        }

        is QrState.Loading -> {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        QrState.Success -> {
            viewModel.backToHome(navigateToHome)
        }

        is QrState.Error -> {
            Log.d("QrScreen", (qrState as QrState.Error).message)
        }
    }
}