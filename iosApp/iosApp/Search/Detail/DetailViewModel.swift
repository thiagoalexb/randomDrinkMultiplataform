//
//  DetailViewModel.swift
//  iosApp
//
//  Created by Thiago Alex on 15/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class DetailViewModel: ObservableObject {
    
    private var getDrinkById: GetDrinkByIdUseCase
    private var saveFavoriteUseCase: SaveFavoriteDrinksUseCase
    private var deleteFavoriteUseCase: DeleteFavoriteDrinksUseCase
    private var getFavoritesUseCase: GetFavoritesDrinksUseCase
    
    @Published var drink: Drink?
    @Published var isFavorite: Bool = false
    
    init(
        getDrinkById: GetDrinkByIdUseCase,
        saveFavoriteUseCase: SaveFavoriteDrinksUseCase,
        deleteFavoriteUseCase: DeleteFavoriteDrinksUseCase,
        getFavoritesUseCase: GetFavoritesDrinksUseCase
    ) {
        self.getDrinkById = getDrinkById
        self.saveFavoriteUseCase = saveFavoriteUseCase
        self.deleteFavoriteUseCase = deleteFavoriteUseCase
        self.getFavoritesUseCase = getFavoritesUseCase
    }
    
    func getDrinkById(id: String) async {
        do {
            let drinkResult = try await getDrinkById.invoke(id: id)
            drinkResult.getResult(successFunc: { drink in
                DispatchQueue.main.async {
                    self.drink = drink
                }
            }, errorFunc: { generalError in
                
            })
        } catch {
            
        }
    }
    
    func getIsFavorite() async {
        do {
            let favoritesResult = try await getFavoritesUseCase.invoke()
            
            favoritesResult.getResults { drinks in
                self.isFavorite = drinks.contains { drink in
                    self.drink?.id == drink.id
                }
            } errorFunc: { generalError in
                self.isFavorite = false
            }
        } catch {
            self.isFavorite = false
        }
    }
    
    func saveOrDeleteFavorite(drink: Drink, isFavorite: Bool) async {
        do {
            if isFavorite {
                try await saveFavoriteUseCase.invoke(drink: drink)
            } else {
                try await deleteFavoriteUseCase.invoke(drink: drink)
            }
            self.isFavorite = isFavorite
        } catch {
            
        }
    }
}
