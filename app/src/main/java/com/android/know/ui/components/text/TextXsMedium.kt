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
import com.android.know.ui.FontWeightConst

@Composable
fun TextXsMedium(
    text: String,
    color: Color = colorResource(id = R.color.content_tertiary),
) {
    Text(
        text = text,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight(FontWeightConst.MEDIUM),
        color = color
    )
}
