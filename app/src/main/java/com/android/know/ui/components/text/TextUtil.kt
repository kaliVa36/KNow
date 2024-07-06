package com.android.know.ui.components.text

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.android.know.R
import com.android.know.ui.TrailingDotConst

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontStyle: FontStyle? = null,
    text: String,
    textAlign: TextAlign? = null,
) {
    var isVisualOverflow by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableIntStateOf(TrailingDotConst.START_INDEX) }
    Box(
        modifier = Modifier.then(modifier)
    ) {
        Text(
            modifier = textModifier
                .fillMaxWidth()
                .animateContentSize(),
            text = buildAnnotatedString {
                if (isVisualOverflow) {
                    val adjustText = text.substring(startIndex = TrailingDotConst.START_INDEX, endIndex = lastCharIndex.coerceAtMost(text.length))
                        .dropLastWhile { Character.isWhitespace(it) || it == TrailingDotConst.DOT }
                    append(adjustText)
                    append(stringResource(id = R.string.trailing_dots))
                    append(TrailingDotConst.NEW_LINE)
                } else {
                    append(text)
                }
            },
            maxLines = TrailingDotConst.MAX_LINE_LENGTH,
            fontStyle = fontStyle,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.hasVisualOverflow) {
                    isVisualOverflow = true
                    lastCharIndex = textLayoutResult
                        .getLineEnd(TrailingDotConst.MAX_LINE_LENGTH - TrailingDotConst.INDEX_CORRECTION)
                }
            },
            style = style,
            textAlign = textAlign,
            color = colorResource(id = R.color.text_tertiary),
            fontSize = 24.sp
        )
    }
}
