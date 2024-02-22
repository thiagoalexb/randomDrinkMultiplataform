package com.quintallabs.randomdrink.di.datasource.api

import com.quintallabs.randomdrink.repository.datasource.api.DrinkApi
import com.quintallabs.randomdrink.repository.datasource.base.ApiUtil
import org.koin.dsl.module

val apiModule = module {
  single { ApiUtil() }
  factory { DrinkApi(get()) }
}