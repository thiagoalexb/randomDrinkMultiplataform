package com.quintallabs.randomdrink.repository.datasource.base

import com.quintallabs.randomdrink.domain.models.commons.GeneralError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class ApiUtil {

  suspend fun <I> safeApiCall(
    apiCall: suspend () -> I
  ): ApiResultWrapper<I> {

    return withContext(Dispatchers.IO) {
      try {
        ApiResultWrapper.Success(apiCall.invoke())
      } catch (throwable: Throwable) {
        ApiResultWrapper.Error(
          GeneralError(
            title = throwable.message ?: "Network call error",
            detail = throwable.message ?: "Network call error",
            code = 0
          )
        )
      }
    }
  }
}