package com.android.know.ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.know.R

@Composable
fun TextRegular(
    text: String,
    color: Color = colorResource(id = R.color.text_primary)
) {
    Text(
        text = text,
        color = color,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight(400),
    )
}