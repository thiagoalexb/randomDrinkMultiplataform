package com.quintallabs.randomdrink.repository.datasource.base

import com.quintallabs.randomdrink.domain.models.commons.GeneralError
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain

sealed class ApiResultWrapper<out T> {

  data class Success<out T>(val value: T) : ApiResultWrapper<T>()
  data class Error(val error: GeneralError) : ApiResultWrapper<Nothing>()
}

fun <I, O> ApiResultWrapper<I>.mapperToResult(mapper: (I) -> O): ResultDomain<O, GeneralError> {

  return when(this) {
    is ApiResultWrapper.Error -> {
      ResultDomain.Error(this.error)
    }
    is ApiResultWrapper.Success -> {
      ResultDomain.Success(mapper(this.value))
    }
  }

}