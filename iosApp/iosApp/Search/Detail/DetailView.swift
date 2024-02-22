//
//  Detail.swift
//  iosApp
//
//  Created by Thiago Alex on 15/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct DetailView: View {
    
    @StateObject var viewModel: DetailViewModel
    var drinkId: String
    
    var body: some View {
        ZStack(alignment: .bottomTrailing) {
            VStack {
                if let drink = viewModel.drink {
                    Card(
                        drink: drink,
                        isFavorite: viewModel.isFavorite,
                        onFavoriteClick: { drink, isFavorite in
                            await viewModel.saveOrDeleteFavorite(drink: drink, isFavorite: isFavorite)
                        }
                    ).task {
                        await viewModel.getIsFavorite()
                    }
                }
            }
        }.task {
            await viewModel.getDrinkById(id: drinkId)
        }
        .toolbar(.hidden, for: .tabBar)
    }
}
