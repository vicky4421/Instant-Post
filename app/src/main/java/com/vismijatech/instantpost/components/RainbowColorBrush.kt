package com.vismijatech.instantpost.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

/**
 * Created by Vivek Sawant on 03-12-2023.
 */

val rainbowColors = listOf(
    Color(color = 0xFFFF0000),
    Color(color = 0xFFFF7F00),
    Color(color = 0xFFFFFF00),
    Color(color = 0xFF00FF00),
    Color(color = 0xFF0000FF),
    Color(color = 0xFF4B0082),
    Color(color = 0xFF9400D3)
)

val rainbowBrush = Brush.radialGradient(
    rainbowColors,
    center = Offset(0.5f, 0.5f),
    radius = 1.0f,
    tileMode = TileMode.Repeated
)