package com.example.productslistapp.data.network

import com.example.productslistapp.domain.models.Product
import com.example.productslistapp.domain.models.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @GET("products")
    suspend fun getProductByPage(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
    ): Products
}