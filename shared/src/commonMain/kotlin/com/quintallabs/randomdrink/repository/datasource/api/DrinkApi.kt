package com.quintallabs.randomdrink.repository.datasource.api

import com.quintallabs.randomdrink.data.dto.RandomDrinkResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path

class DrinkApi(
  private val httpClient: HttpClient
) {

  suspend fun getRandomDrink(): RandomDrinkResponseDTO {
    return httpClient
      .get("random.php")
      .body()
  }

  suspend fun searchDrink(query: String): RandomDrinkResponseDTO{
    return httpClient
      .get {
        url {
          protocol = URLProtocol.HTTPS
          path("filter.php")
          parameters.append("i", query)
        }
      }
      .body()
  }

  suspend fun getById(id: String): RandomDrinkResponseDTO {
    return httpClient
      .get {
        url {
          protocol = URLProtocol.HTTPS
          path("lookup.php")
          parameters.append("i", id)
        }
      }
      .body()
  }

}