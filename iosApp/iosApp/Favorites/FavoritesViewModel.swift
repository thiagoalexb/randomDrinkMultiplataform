//
//  FavoritesViewModel.swift
//  iosApp
//
//  Created by Thiago Alex on 15/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class FavoritesViewModel: ObservableObject {
    private var saveFavoriteUseCase: SaveFavoriteDrinksUseCase
    private var deleteFavoriteUseCase: DeleteFavoriteDrinksUseCase
    private var getFavoritesUseCase: GetFavoritesDrinksUseCase
    
    @Published var drinks: [Drink] = []
    
    init(saveFavoriteUseCase: SaveFavoriteDrinksUseCase, deleteFavoriteUseCase: DeleteFavoriteDrinksUseCase, getFavoritesUseCase: GetFavoritesDrinksUseCase) {
        self.saveFavoriteUseCase = saveFavoriteUseCase
        self.deleteFavoriteUseCase = deleteFavoriteUseCase
        self.getFavoritesUseCase = getFavoritesUseCase
    }
    
    func getFavorites() async {
        do {
            let favoritesResult = try await getFavoritesUseCase.invoke()
            
            favoritesResult.getResults { drinks in
                self.drinks = drinks
            } errorFunc: { generalError in
                
            }
        } catch {
            
        }
    }
    
    func deleteFavorite(drink: Drink) async {
        do {
            try await deleteFavoriteUseCase.invoke(drink: drink)
            
            let index = drinks.firstIndex(where: { drinkFavorite in
                drink.id == drinkFavorite.id
            }) ?? -1
            
            drinks.remove(at: index)
        } catch {
            
        }
    }
}
