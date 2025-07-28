package org.example.project.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

// Created by Zain Shakoor
// on 2/11/2025

@Composable
inline fun BaseScreen(
    background: Color = Color.White,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .background(color = background),
        content = content,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    )
}

@Preview
@Composable
private fun BaseScreenPreview() {
    BaseScreen { }
}