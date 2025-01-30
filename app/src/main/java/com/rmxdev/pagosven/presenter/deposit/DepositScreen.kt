package com.rmxdev.pagosven.presenter.deposit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmxdev.pagosven.ui.theme.Black
import com.rmxdev.pagosven.ui.theme.OffWhite
import com.rmxdev.pagosven.ui.theme.White
import com.rmxdev.pagosven.ui.theme.Yellow

@Composable
fun DepositScreen(modifier: Modifier = Modifier) {

    var cvu by rememberSaveable { mutableStateOf("") }
    var alias by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(text = "Datos de tu cuenta", fontSize = 35.sp, color = Black)
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
        Text(
            text = "Tu CVU",
            fontSize = 20.sp,
            color = Black,
            textAlign = TextAlign.Center
        )
        TextField(
            value = cvu,
            onValueChange = {  },
            enabled = false,
            label = { Text("0002154789658742365412", color = Black) },
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .clip(CircleShape),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Yellow,
                focusedContainerColor = Yellow,
                focusedTextColor = Black,
                unfocusedTextColor = Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledTextColor = Black,
                disabledContainerColor = Yellow,
                disabledIndicatorColor = Color.Transparent
            ),
            maxLines = 1
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = "Tu alias",
            fontSize = 20.sp,
            color = Black,
            textAlign = TextAlign.Center
        )
        TextField(
            value = alias,
            onValueChange = {  },
            enabled = false,
            label = { Text("alias.prueba.pv", color = Black) },
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .clip(CircleShape),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Yellow,
                focusedContainerColor = Yellow,
                focusedTextColor = Black,
                unfocusedTextColor = Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledTextColor = Black,
                disabledContainerColor = Yellow,
                disabledIndicatorColor = Color.Transparent
            ),
            maxLines = 1
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}