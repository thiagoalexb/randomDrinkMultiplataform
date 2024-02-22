//
//  FavoritesView.swift
//  iosApp
//
//  Created by Thiago Alex on 11/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FavoritesView: View {
    
    @StateObject var viewModel: FavoritesViewModel
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                if !viewModel.drinks.isEmpty {
                    
                    ForEach(viewModel.drinks, id: \.self.id) { drink in
                        Card(
                            drink: drink,
                            isFavorite: drink.isFavorite,
                            onFavoriteClick: { drink, isFavorite in
                                await viewModel.deleteFavorite(drink: drink)
                            }
                        )
                    }
                } else {
                    Text("Nenhum favorito adicionado")
                        .font(.title)
                }
            }.task {
                await viewModel.getFavorites()
            }
        }
    }
}
