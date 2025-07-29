package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.example.project.effect.PhotoEffects
import org.example.project.intent.PhotoIntent
import org.example.project.repo.PhotoRepository
import org.example.project.state.PhotoUiState

// Created by Zain Shakoor
// on 7/24/2025

class PhotoViewModel(private val photoRepository: PhotoRepository) : ViewModel() {

    private val _state = MutableStateFlow(PhotoUiState())
    val state: StateFlow<PhotoUiState> = _state

    private val _effect = MutableSharedFlow<PhotoEffects>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: PhotoIntent) {
        when (intent) {
            PhotoIntent.loadPhotos -> {
                fetchPhotos()
            }
        }
    }


    private fun fetchPhotos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val photos = photoRepository.getAllPhotoDetails()
                _state.value = _state.value.copy(photos = photos, isLoading = false, error = null)
                _effect.emit(PhotoEffects.ShowToast("Data is Loaded SucccesFully"))
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
                _effect.emit(PhotoEffects.ShowError("Something Went Wrong"))
            }
        }
    }


}




