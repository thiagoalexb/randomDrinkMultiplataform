package com.quintallabs.randomdrink.android

import android.app.Application
import com.quintallabs.randomdrink.android.di.viewModelModule
import com.quintallabs.randomdrink.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RandomDrinkApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    initApplication()
  }

  private fun initApplication() {
    setupKoin()
  }

  private fun setupKoin() {
    startKoin {
      androidContext(this@RandomDrinkApplication)
      modules(
        appModule() + viewModelModule
      )
    }
  }
}