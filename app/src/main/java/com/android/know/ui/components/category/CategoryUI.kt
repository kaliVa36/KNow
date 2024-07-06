package com.android.know.ui.components.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.android.know.R
import com.android.know.ui.components.text.TextSmMedium

@Composable
fun CategoryUI(category: String, onClick: () -> Unit, isSelected: Boolean = false) {
    Box(modifier = Modifier
        .clickable { onClick() }
        .clip(RoundedCornerShape(99))
        .background(
            if (isSelected) {
                colorResource(id = R.color.content_primary)
            } else {
                colorResource(
                    id = R.color.content_secondary
                )
            },
            RoundedCornerShape(99)
        )
        .padding(
            horizontal = dimensionResource(id = R.dimen.btn_category_horizontal_padding),
            vertical = dimensionResource(id = R.dimen.btn_category_vertical_padding)
        )
    ) {
        TextSmMedium(
            category, color = if (isSelected) {
                colorResource(id = R.color.white)
            } else {
                colorResource(id = R.color.text_secondary)
            }
        )
    }
}