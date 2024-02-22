package com.quintallabs.randomdrink.android.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun DrinkTitle(title: String, modifier: Modifier = Modifier) {
  Text(
    text = title,
    style = MaterialTheme.typography.headlineMedium,
    color = MaterialTheme.colorScheme.onPrimary,
    overflow = TextOverflow.Ellipsis,
    maxLines = 1,
    modifier = modifier
  )
}