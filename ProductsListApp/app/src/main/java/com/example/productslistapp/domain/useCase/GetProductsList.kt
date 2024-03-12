package com.example.productslistapp.domain.useCase

import com.example.productslistapp.domain.models.Products
import com.example.productslistapp.domain.repository.ProductRepository

class GetProductsList(private val productRepository: ProductRepository) {

    suspend fun getProductsList(): Products {
        return productRepository.getProductsList()
    }
}

