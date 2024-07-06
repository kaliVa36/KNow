package com.android.know.ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.know.R

@Composable
fun Heading(text: String) {
    Text(
        text = text,
        fontSize = 28.sp,
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight(600),
        lineHeight = 24.sp,
        color = colorResource(id = R.color.text_tertiary)
    )
}
