//
//  HomeView.swift
//  iosApp
//
//  Created by Thiago Alex on 11/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeView: View {
    
    @StateObject var viewModel: HomeViewModel
    
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
            
            Button {
                Task {
                    await viewModel.getRandomDrink()
                }
            } label: {
                Image(systemName: "arrow.clockwise")
                    .font(.title2.weight(.semibold))
                    .padding()
                    .background(Color.pink)
                    .foregroundColor(.white)
                    .clipShape(Rectangle())
                    .cornerRadius(15)
                    .shadow(radius: 4, x: 0, y: 4)
            }
            .padding()
        }.task {
            await viewModel.getRandomDrink()
        }
    }
}
