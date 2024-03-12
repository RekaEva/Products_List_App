package com.example.productslistapp.data.repository

import com.example.productslistapp.data.network.ProductApi
import com.example.productslistapp.domain.models.Product
import com.example.productslistapp.domain.models.Products
import com.example.productslistapp.domain.repository.ProductRepository

class ProductRepositoryImpl(private val api: ProductApi) : ProductRepository {

    override suspend fun getProductById(id: Int): Product {
        return api.getProductById(id)
    }

    override suspend fun getProductsList(): Products {
        val limit = LIMIT
        var skip = SKIP
        val resultProducts = mutableListOf<Product>()
        while (true) {
            val products = api.getProductByPage(limit = limit, skip = skip)
            resultProducts.addAll(products.products)
            skip += limit
            if (skip >= products.total) {
                break
            }
        }
        return Products(resultProducts)
    }

    companion object {
        const val LIMIT = 20
        const val SKIP = 0
    }
}
