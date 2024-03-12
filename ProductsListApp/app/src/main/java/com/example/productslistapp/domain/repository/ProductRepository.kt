package com.example.productslistapp.domain.repository

import com.example.productslistapp.domain.models.Product
import com.example.productslistapp.domain.models.Products

interface ProductRepository {

    suspend fun getProductById(id: Int): Product

    suspend fun getProductsList(): Products
}

