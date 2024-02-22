//
//  View+.swift
//  iosApp
//
//  Created by Thiago Alex on 21/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension View {
    func underlineTextField() -> some View {
        self
            .padding(.vertical, 10)
            .overlay(Rectangle().frame(height: 2).padding(.top, 35))
            .foregroundColor(.darkPink)
            .padding(10)
    }
}
