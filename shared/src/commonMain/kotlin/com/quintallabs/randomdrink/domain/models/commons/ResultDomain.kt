package com.quintallabs.randomdrink.domain.models.commons

sealed class ResultDomain<out D, out E> {
  data class Success<D>(val data: D): ResultDomain<D, Nothing>()
  data class Error<E>(val error: E): ResultDomain<Nothing, E>()
}