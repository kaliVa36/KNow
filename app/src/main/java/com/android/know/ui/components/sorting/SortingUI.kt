package com.android.know.ui.components.sorting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.know.R
import com.android.know.ui.ImageContentDescription
import com.android.know.ui.components.text.TextSmMedium

@Composable
fun SortingUI(category: String, onClick: () -> Unit = {}, isSelected: Boolean = false, onRemoveClick: () -> Unit = {}) {
    Row(modifier = Modifier
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
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextSmMedium(
            category, color = if (isSelected) {
                colorResource(id = R.color.white)
            } else {
                colorResource(id = R.color.text_secondary)
            }
        )
        if (isSelected) {
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(id = R.drawable.remove),
                contentDescription = ImageContentDescription.REMOVE,
                modifier = Modifier
                    .size(8.dp)
                    .clickable { onRemoveClick() }
            )
        }
    }
}