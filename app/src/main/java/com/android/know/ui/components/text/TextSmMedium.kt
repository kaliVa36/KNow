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
fun TextMedium(
    text: String,
    color: Color = colorResource(id = R.color.text_secondary),
) {
    Text(
        text = text,
        color = color,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight(500)
    )
}
