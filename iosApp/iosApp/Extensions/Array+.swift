//
//  Array+.swift
//  iosApp
//
//  Created by Thiago Alex on 21/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

extension Array {

    func get(index: Int) -> Element? {
        if 0 <= index && index < count {
            return self[index]
        } else {
            return nil
        }
    }
}
