//
//  SearchViewModel.swift
//  iosApp
//
//  Created by Thiago Alex on 15/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class SearchViewModel: ObservableObject {
    
    private var searchDrinkUseCase: SearchDrinkUseCase
    
    @Published var seachDrinks: [Drink] = []
    
    init(searchDrinkUseCase: SearchDrinkUseCase) {
        self.searchDrinkUseCase = searchDrinkUseCase
    }
    
    func searchDrink(ingredient: String) async {
        do {
            try await searchDrinkUseCase.invoke(query: ingredient).getResults { drinks in
                self.seachDrinks = drinks
            } errorFunc: { generalError in
                seachDrinks = []
            }

        } catch {
            seachDrinks = []
        }
    }
}
