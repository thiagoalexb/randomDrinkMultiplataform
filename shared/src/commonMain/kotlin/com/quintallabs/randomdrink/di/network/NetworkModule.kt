package com.quintallabs.randomdrink.di.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val networkModule = module {
  single {
    HttpClient {
      install(ContentNegotiation) {
        json()
      }
      defaultRequest {
        url("https://www.thecocktaildb.com/api/json/v1/1/")
      }
    }
  }
}