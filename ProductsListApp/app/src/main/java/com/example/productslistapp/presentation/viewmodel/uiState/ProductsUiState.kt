package com.example.productslistapp.presentation.viewmodel.uiState

import com.example.productslistapp.domain.models.Products

data class ProductsUiState(
    val list: Products = Products(products = emptyList()),
    val error: Exception? = null,
    val isLoading: Boolean = false
)