//
//  Card.swift
//  iosApp
//
//  Created by Thiago Alex on 21/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct Card: View {
    
    let drink: Drink
    let isFavorite: Bool
    let onFavoriteClick: (_ drink: Drink, _ isFavorite: Bool) async -> Void
    
    var body: some View {
        ScrollView {
            VStack {
                if let imageUrl = drink.drinkThumb {
                    
                    
                    AsyncImage(
                        url: URL(string: imageUrl),
                        content: { image in
                            image.resizable()
                                .scaledToFit()
                        },
                        placeholder: {
                            ProgressView()
                        }
                    )
                }
                
                
                VStack(alignment: .leading) {
                    HStack {
                        Text(drink.drinkName)
                            .font(.title)
                        
                        Spacer()
                        
                        let favoriteIcon = isFavorite ? "heart.fill" : "heart"
                        Button {
                            Task {
                                await onFavoriteClick(drink, !isFavorite)
                            }
                        } label: {
                            Image(systemName: favoriteIcon)
                                .foregroundColor(.red)
                        }
                    }
                    .padding(.bottom, 2)
                    
                    Text("Ingredients")
                        .font(.title2)
                    
                    ForEach(Array(drink.ingredients.enumerated()), id: \.element) { index, ingredient in
                        
                        
                        if let measure = drink.measures.get(index: index) {
                            let text = "\(ingredient) - \(measure)"
                            Text(text)
                                .font(.callout)
                                .padding(.leading, 12)
                                .padding(.top, 2)
                        } else {
                            Text(ingredient)
                                .font(.callout)
                                .padding(.leading, 12)
                                .padding(.top, 2)
                        }
                    }
                    
                    
                }.padding()
            }
            .cornerRadius(10)
            .overlay(
                RoundedRectangle(cornerRadius: 10, style: .continuous)
                    .stroke(Color(.sRGB, red: 150/255, green: 150/255, blue: 150/255, opacity: 0.6), lineWidth: 1.5)
                
            )
            .padding([.top, .horizontal])
        }
        Spacer()
    }
}
