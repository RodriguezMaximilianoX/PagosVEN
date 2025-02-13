package com.rmxdev.pagosven.presenter.transfer.transferFindUser

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmxdev.pagosven.R
import com.rmxdev.pagosven.domain.entities.User
import com.rmxdev.pagosven.ui.theme.Black
import com.rmxdev.pagosven.ui.theme.Blue
import com.rmxdev.pagosven.ui.theme.White

@Composable
fun TransferScreen(
    modifier: Modifier = Modifier,
    viewModel: TransferViewModel = hiltViewModel(),
    navigateToAmount: (User) -> Unit
) {

    var identifier by rememberSaveable { mutableStateOf("") }
    val transferState by viewModel.transferState.collectAsState()
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
            Text(text = "Transferencia", fontSize = 35.sp, color = Black)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 54.dp, vertical = 16.dp)
                    .height(3.dp)
                    .background(
                        Black
                    )
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "Buscar por:",
                fontSize = 30.sp,
                color = Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Número de teléfono",
                fontSize = 25.sp,
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Número de cédula",
                fontSize = 25.sp,
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Alias",
                fontSize = 25.sp,
                color = Black,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.weight(0.25f))
            TextField(
                value = identifier,
                onValueChange = { identifier = it },
                label = { Text("Ingresa los datos de la cuenta", fontWeight = FontWeight.Bold) },
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
                onClick = { viewModel.fetchReceiverInfo(identifier) },
                colors = buttonColors(containerColor = Blue, contentColor = White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                enabled = identifier.isNotBlank()
            ) {
                Text(
                    text = "Buscar",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            when (transferState) {
                is TransferState.Loading -> {
                    CircularProgressIndicator()
                }

                is TransferState.Success -> {

                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(White),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Nombre: ${(transferState as TransferState.Success).receiver.name}", fontSize = 25.sp,)
                        Text(text = "Apellido: ${(transferState as TransferState.Success).receiver.surname}", fontSize = 25.sp,)
                        Text(text = "Cédula: ${(transferState as TransferState.Success).receiver.dni}", fontSize = 25.sp,)
                        Text(text = "Alias: ${(transferState as TransferState.Success).receiver.alias}", fontSize = 25.sp,)
                        Text(text = "Tleléfono: ${(transferState as TransferState.Success).receiver.phone}", fontSize = 25.sp,)
                        val receiver = (transferState as TransferState.Success).receiver
                        Button(
                            onClick = {
                                navigateToAmount(receiver)
                            },
                            colors = buttonColors(containerColor = Blue, contentColor = White),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 24.dp)
                        ) {
                            Text(
                                text = "Continuar",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                is TransferState.Error -> {
                    LaunchedEffect(transferState) {
                        Toast.makeText(
                            context, "Usuario no encontrado", Toast.LENGTH_LONG
                        ).show()
                    }
                }

                else -> {}

            }
            Spacer(modifier = Modifier.weight(1f))
        }

    }

}