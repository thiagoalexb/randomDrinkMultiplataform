package com.quintallabs.randomdrink.android.di

import com.quintallabs.randomdrink.android.presentation.favorites.FavoritesViewModel
import com.quintallabs.randomdrink.android.presentation.home.HomeViewModel
import com.quintallabs.randomdrink.android.presentation.search.SearchViewModel
import com.quintallabs.randomdrink.android.presentation.search.detail.DrinkDetailViewModel
import com.quintallabs.randomdrink.database.DriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val viewModelModule = module {
  factory { HomeViewModel(get(), get(), get(), get()) }
  factory { FavoritesViewModel(get(), get()) }
  factory { SearchViewModel(get()) }
  factory { DrinkDetailViewModel(get(), get(), get(), get()) }
  factory { DriverFactory(androidContext()) }
}