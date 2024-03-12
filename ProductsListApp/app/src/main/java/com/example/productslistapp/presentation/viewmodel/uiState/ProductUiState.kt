package com.example.productslistapp.presentation.viewmodel.uiState

import com.example.productslistapp.domain.models.Product


data class ProductUiState(
    val productData: Product? = null,
    val error: Exception? = null,
    val isLoading: Boolean = false
)