//
//  SearchView.swift
//  iosApp
//
//  Created by Thiago Alex on 11/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchView: View {
    
    @StateObject var viewModel: SearchViewModel
    
    @State private var ingredient: String = ""
    
    var body: some View {
        NavigationStack {
                VStack(alignment: .leading) {
                    HStack {
                        TextField("Ingredients", text: $ingredient) { Bool in
                            if ingredient.count > 3 {
                                Task {
                                    await viewModel.searchDrink(ingredient: ingredient)
                                }
                            }
                        }
                        Spacer()
                        Image(systemName: "magnifyingglass")
                    }.underlineTextField()
                    
                    ScrollView {
                        VStack(alignment: .leading) {
                            ForEach(viewModel.seachDrinks, id:\.id) { drink in
                                NavigationLink {
                                    DetailView(
                                        viewModel: DetailViewModel(
                                            getDrinkById: UseCasesComponent().getDrinkByIdUseCase(),
                                            saveFavoriteUseCase: UseCasesComponent().getSaveFavoritesDrinksUseCase(),
                                            deleteFavoriteUseCase: UseCasesComponent().getDeleteFavoriteDrinksUseCase(),
                                            getFavoritesUseCase: UseCasesComponent().getFavoritesDrinksUseCase()),
                                        drinkId: drink.id
                                    )
                                } label: {
                                    
                                    VStack(alignment: .leading) {
                                        Text(drink.drinkName)
                                            .font(.headline)
                                            .padding()
                                    }
                                    .frame(maxWidth: .infinity)
                                    .cornerRadius(10)
                                    .overlay(
                                        RoundedRectangle(cornerRadius: 10, style: .continuous)
                                            .stroke(Color(.sRGB, red: 150/255, green: 150/255, blue: 150/255, opacity: 0.6), lineWidth: 1.5)
                                        
                                    )
                                }

                            }
                        }
                    }
                }.padding()
                
        }
        .navigationBarHidden(true)
        
    }
}
