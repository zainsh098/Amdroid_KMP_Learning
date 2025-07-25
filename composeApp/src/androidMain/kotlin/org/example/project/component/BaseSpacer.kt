package org.example.project.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Created by Zain Shakoor
// on 7/25/2025


@Composable
fun BaseSpacer(height: Dp = 16.dp, width: Dp = 10.dp) {
    Spacer(
        Modifier
            .height(height)
            .width(width)
    )
}
