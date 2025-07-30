package org.example.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.example.project.model.Product

class SharedViewModel : ViewModel() {
    var selectedProduct by mutableStateOf<Product?>(null)

}