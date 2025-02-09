package com.satoshi.lightningnodes.feature.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.satoshi.lightningnodes.R

@Composable
fun ErrorScreen(error: String, onRetry: () -> Unit) {
    val animation by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            R.raw.error_state
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = animation,
            iterations = LottieConstants.IterateForever
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = error)
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = { onRetry() }) {
            Text(text = stringResource(R.string.retry))
        }
    }
}