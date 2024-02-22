//
//  ResultDomain+.swift
//  iosApp
//
//  Created by Thiago Alex on 21/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension ResultDomain {
    
    @objc func getResult(
        successFunc: (_ drink: Drink) -> Void,
        errorFunc: (_ generalError: GeneralError) -> Void
    ) {
        switch self {
        case let errorDomain as ResultDomainError<GeneralError>:
            errorFunc(errorDomain.error!)
            break
        case let successDomain  as ResultDomainSuccess<Drink>:
            successFunc(successDomain.data!)
            break
        default:
            print("default")
        }
    }
    
    @objc func getResults(
        successFunc: (_ drinks: Array<Drink>) -> Void,
        errorFunc: (_ generalError: GeneralError) -> Void
    ) {
        switch self {
        case let errorDomain as ResultDomainError<GeneralError>:
            errorFunc(errorDomain.error!)
            break
        case let successDomain as ResultDomainSuccess<AnyObject>:
            successFunc(successDomain.data! as! Array<Drink>)
            break
        default:
            print("default")
        }
    }
}
