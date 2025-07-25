package org.example.project.effect

// Created by Zain Shakoor
// on 7/24/2025

sealed class PhotoEffects {
    data class ShowToast(val mesg: String) : PhotoEffects()
    data class ShowError(val mesg: String) : PhotoEffects()
}
