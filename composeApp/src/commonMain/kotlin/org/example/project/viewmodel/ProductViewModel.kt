package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.effect.ProductEffects
import org.example.project.intent.ProductIntent
import org.example.project.repo.ProductRepository
import org.example.project.state.ProductUiState

// Created by Zain Shakoor
// on 7/29/2025

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _productState = MutableStateFlow(ProductUiState())
    val productUiState = _productState.asStateFlow()

    private val _effects = Channel<ProductEffects>(Channel.BUFFERED)
    val effects = _effects

    fun onIntent(intent: ProductIntent) {
        when (intent) {
            ProductIntent.LoadProductandDetails -> {
                getProducts()
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val result = repository.getAllProductDetails()
                _productState.value = _productState.value.copy(
                    products = result, isLoading = false
                )
                _effects.send(ProductEffects.ShowToast("Data is Loaded SucccesFully"))
            } catch (e: Exception) {
                _productState.value.copy(isLoading = false, error = e.message.toString())
                _effects.send(ProductEffects.ShowError("Some Thing Went Wrong!🤖 Data is not Loaded"))
            }

        }
    }


}