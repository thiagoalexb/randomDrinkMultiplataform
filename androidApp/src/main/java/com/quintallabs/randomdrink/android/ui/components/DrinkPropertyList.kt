package com.quintallabs.randomdrink.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrinkPropertyList(
  sectionTitle: String,
  ingredients: List<String>,
  measures: List<String>,
) {
  Column {
    Text(
      modifier = Modifier
        .padding(bottom = 12.dp),
      text = sectionTitle,
      style = MaterialTheme.typography.titleLarge,
      color = MaterialTheme.colorScheme.onPrimary
    )

    for ((index, ingredient) in ingredients.withIndex()) {

      if(ingredient.isBlank())
        continue

      var text = ingredient
      val measure = try {
        measures[index]
      } catch (throwable: Throwable) {
        ""
      }
      text += if (measure.isNotBlank())
        " - $measure"
      else
        ""

      Text(
        modifier = Modifier
          .padding(start = 12.dp, bottom = 12.dp),
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onPrimary
      )
    }
  }
}