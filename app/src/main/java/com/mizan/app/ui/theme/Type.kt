package com.mizan.app.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

// إعداد أساسي للطباعة (يمكنك تخصيص الخطوط لاحقًا)
val Typography = Typography(
    h5 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 24.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 14.sp
    )
)
