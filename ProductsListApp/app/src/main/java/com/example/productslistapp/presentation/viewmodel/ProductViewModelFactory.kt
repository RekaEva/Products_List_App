package com.example.productslistapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.productslistapp.data.network.ApiFactory
import com.example.productslistapp.data.repository.ProductRepositoryImpl
import com.example.productslistapp.domain.useCase.GetProductById
import com.example.productslistapp.domain.useCase.GetProductsList

class ProductViewModelFactory : ViewModelProvider.Factory {

    private val api = ApiFactory().apiService

    private val productRepository by lazy {
        ProductRepositoryImpl(api)
    }
    private val getProductsByPage by lazy {
        GetProductsList(productRepository)
    }
    private val getProductById by lazy {
        GetProductById(productRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductsViewModel(getProductsByPage) as T
        } else {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(getProductById) as T
        }
    }
}


