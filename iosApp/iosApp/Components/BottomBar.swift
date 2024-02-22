//
//  BottomBar.swift
//  iosApp
//
//  Created by Thiago Alex on 21/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BottomBar: View {
    var body: some View {
        TabView {
            HomeView(
                viewModel: HomeViewModel(
                    getRandomDrinkUseCase: UseCasesComponent().getRandomDrinkUseCase(),
                    saveFavoriteUseCase: UseCasesComponent().getSaveFavoritesDrinksUseCase(),
                    deleteFavoriteUseCase: UseCasesComponent().getDeleteFavoriteDrinksUseCase(),
                    getFavoritesUseCase: UseCasesComponent().getFavoritesDrinksUseCase()
                )
            )
            .tabItem {
                Image(systemName: "house.fill")
                Text("Home")
            }
            FavoritesView(
                viewModel: FavoritesViewModel(
                    saveFavoriteUseCase: UseCasesComponent().getSaveFavoritesDrinksUseCase(),
                    deleteFavoriteUseCase: UseCasesComponent().getDeleteFavoriteDrinksUseCase(),
                    getFavoritesUseCase: UseCasesComponent().getFavoritesDrinksUseCase()
                )
            )
            .tabItem {
                Image(systemName: "heart.fill")
                Text("Favorites")
            }
            
            SearchView(
                viewModel: SearchViewModel(searchDrinkUseCase: UseCasesComponent().searchDrinkUseCase())
            )
            .tabItem {
                Image(systemName: "magnifyingglass")
                Text("Search")
            }
        }
    }
}

#Preview {
    BottomBar()
}
