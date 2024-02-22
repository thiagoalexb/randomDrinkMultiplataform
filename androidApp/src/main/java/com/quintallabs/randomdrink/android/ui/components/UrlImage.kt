package com.quintallabs.randomdrink.android.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun UrlImage(drinkThumb: String?, drinkContentDescription: String?) {

  val painter = rememberAsyncImagePainter(model = drinkThumb)
  val state = painter.state

  val transition by animateFloatAsState(
    targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f, label = ""
  )
  if (state is AsyncImagePainter.State.Loading) {
    ImageLoadingAnimation()
  } else if(state is AsyncImagePainter.State.Success) {
    Image(
      painter = painter,
      contentDescription = drinkContentDescription,
      modifier = Modifier
        .alpha(transition)
    )
  }
}