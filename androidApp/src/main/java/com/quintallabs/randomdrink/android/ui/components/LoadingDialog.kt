package com.quintallabs.randomdrink.android.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog(isShowingDialog: Boolean) {
  if (isShowingDialog) {
    Dialog(
      onDismissRequest = { },
      DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = false
      )
    ) {
      DialogContent()
    }
  }
}