package com.example.productslistapp.domain.models

data class Products(
    val products: List<Product>,
    val total: Int = 0
)