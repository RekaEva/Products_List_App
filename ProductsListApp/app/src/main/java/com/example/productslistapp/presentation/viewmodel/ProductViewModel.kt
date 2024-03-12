package com.example.productslistapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productslistapp.domain.useCase.GetProductById
import com.example.productslistapp.presentation.viewmodel.uiState.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productData: GetProductById
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()


    fun getProductData(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.emit(_uiState.value.copy(isLoading = true))
                val data = productData.getProductById(id)
                _uiState.update { currentState ->
                    currentState.copy(
                        productData = data,
                        isLoading = false
                    )
                }
            } catch (errorMessage: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        error = errorMessage,
                        isLoading = false
                    )
                }
            }
        }
    }
}

