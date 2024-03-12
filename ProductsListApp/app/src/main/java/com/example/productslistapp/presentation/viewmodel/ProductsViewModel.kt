package com.example.productslistapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productslistapp.domain.useCase.GetProductsList
import com.example.productslistapp.presentation.viewmodel.uiState.ProductsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productList: GetProductsList
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState: StateFlow<ProductsUiState> = _uiState.asStateFlow()

    fun getProductsList() {
        viewModelScope.launch {
            try {
                _uiState.emit(_uiState.value.copy(isLoading = true))
                val list = productList.getProductsList()
                _uiState.update { currentState ->
                    currentState.copy(
                        list = list,
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

