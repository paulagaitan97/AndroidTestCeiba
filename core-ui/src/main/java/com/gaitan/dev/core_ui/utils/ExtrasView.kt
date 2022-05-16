package com.gaitan.dev.core_ui.utils

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ExtrasView(
    val elevationSmall: Dp = 2.dp,
    val elevationMedium: Dp = 5.dp,
    val elevationLarge : Dp = 15.dp
)

val LocalExtrasView = compositionLocalOf { ExtrasView() }