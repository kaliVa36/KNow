package com.android.know.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.know.R
import com.android.know.ui.ImageContentDescription
import com.android.know.ui.components.text.TextMdRegular

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier, onSearch: () -> Unit) {
    Box(modifier = modifier.wrapContentWidth(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .border(width = 1.dp, color = colorResource(id = R.color.shadow_primary), shape = RoundedCornerShape(size = 12.dp))
                .background(
                    color = colorResource(id = R.color.white),
                    RoundedCornerShape(12.dp)
                )
                .height(40.dp)
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (textField, image) = createRefs()
            TextField(
                value = value,
                onValueChange = onValueChange,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = colorResource(id = R.color.content_primary)
                ),
                placeholder = {
                    TextMdRegular(
                        text = stringResource(id = R.string.search_placeholder),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                },
                modifier = Modifier.constrainAs(textField) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { onSearch() })
            )
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = ImageContentDescription.SEARCH,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(16.dp)
                    .clickable { onSearch() }
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(textField.end)
                    }
            )
        }
    }
}