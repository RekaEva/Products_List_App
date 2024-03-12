package com.example.productslistapp.domain.useCase

import com.example.productslistapp.domain.models.Product
import com.example.productslistapp.domain.repository.ProductRepository

class GetProductById(private val productRepository: ProductRepository) {

    suspend fun getProductById(id: Int): Product {
        return productRepository.getProductById(id)
    }
}