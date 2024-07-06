package com.android.know.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.know.R
import com.android.know.ui.ImageContentDescription
import com.android.know.ui.components.text.TextMedium

@Composable
fun ReadMore(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.content_primary), RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(
                horizontal = dimensionResource(id = R.dimen.btn_read_more_horizontal_padding),
                vertical = dimensionResource(id = R.dimen.btn_read_more_vertical_padding)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextMedium(text = stringResource(id = R.string.read_more), color = colorResource(id = R.color.white))
        Spacer(modifier = Modifier.width(6.dp))
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = ImageContentDescription.ARROW,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun SaveArticle(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(size = 10.dp))
            .border(width = 1.dp, color = colorResource(id = R.color.shadow_primary), shape = RoundedCornerShape(size = 10.dp))
            .background(color = colorResource(id = R.color.white), RoundedCornerShape(size = 10.dp))
            .clickable { onClick() }
            .padding(dimensionResource(id = R.dimen.btn_bookmark_padding))
    ) {
        Image(
            painter = painterResource(id = R.drawable.bookmark),
            contentDescription = ImageContentDescription.BOOKMARK,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(size = 10.dp))
        )
    }
}
