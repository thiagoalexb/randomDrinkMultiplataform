package com.quintallabs.randomdrink.domain.models.commons

data class GeneralError(
  val title: String,
  val detail: String,
  val code: Int
)