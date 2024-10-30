package com.example.loto.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loto.viewModels.LoteriaViewModel

@Composable
fun LoteriaView ( viewModel: LoteriaViewModel ) {
    val lotoNumbers = viewModel.lotoNumbers.value

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if ( lotoNumbers.isEmpty() ) {
            Text (
                text = "Lotería",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            LotteryNumbers ( lotoNumbers, viewModel )
        }

        Button (
            onClick = { viewModel.generateLotoNumbers() }
        ) {
            Text (
                text = "Generar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LotteryNumbers ( lotoNumbers: List<Int>, viewModel: LoteriaViewModel ) {
    LazyRow (
        contentPadding = PaddingValues (
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        items ( lotoNumbers ) { number ->
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(48.dp)
                    .background(Color.Red, CircleShape)
            ) {
                Text(
                    text = number.toString(),
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }

        if ( viewModel.isLoading ) {
            item {
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(48.dp)
                        .background(Color.Red, CircleShape)
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}