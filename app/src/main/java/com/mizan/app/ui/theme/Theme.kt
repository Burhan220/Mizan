package com.mizan.app.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material.lightColors

private val LightColors = lightColors(
    primary = Blue500,
    primaryVariant = Blue700,
    secondary = Teal200
)

@Composable
fun MizanTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
