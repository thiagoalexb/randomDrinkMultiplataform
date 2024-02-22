package com.quintallabs.randomdrink.android.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DialogContent() {
  Box {
    CircularProgressIndicator(
      modifier = Modifier
        .align(
          Alignment.Center
        ),
      color = Color.Red
    )
  }
}