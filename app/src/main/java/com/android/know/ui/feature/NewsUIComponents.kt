package com.android.know.ui.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.android.know.R
import com.android.know.domain.entity.ArticleEntity
import com.android.know.ui.ImageContentDescription
import com.android.know.ui.components.ReadMore
import com.android.know.ui.components.SaveArticle
import com.android.know.ui.components.text.TextSummary
import com.android.know.ui.components.text.Heading
import com.android.know.ui.components.text.TextMdRegular
import com.android.know.ui.components.text.TextSmMedium
import com.android.know.ui.components.text.headingStyle

@Composable
fun ArticleSummaryWithImageUI(
    article: ArticleEntity,
    height: Dp,
    isArticleSummary: Boolean = true,
    onClick: (String) -> Unit = {},
    isSaved: Boolean = false,
    onSave: () -> Unit,
) {
    val unit = height / 6
    val modifier = if (isArticleSummary) {
        Modifier.height(height)
    } else {
        Modifier.heightIn(height)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .shadow(
                elevation = 2.dp,
                spotColor = colorResource(id = R.color.text_secondary),
                ambientColor = colorResource(id = R.color.text_secondary)
            )
            .background(color = colorResource(id = R.color.white))
    ) {
        SubcomposeAsyncImage(
            model = article.url,
            contentDescription = ImageContentDescription.ASYNC_IMAGE,
            modifier = Modifier
                .fillMaxWidth()
                .height(unit * 3),
            contentScale = ContentScale.Crop
        ) {
            when (painter.state) {
                AsyncImagePainter.State.Empty -> {} // TODO: Empty placeholder
                is AsyncImagePainter.State.Error -> {} // TODO: Error handling
                is AsyncImagePainter.State.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.text_secondary).copy(0.4f)), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(16.dp), color = colorResource(id = R.color.content_primary))
                    }
                }

                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            }
        }
        ArticleSummaryUI(
            article,
            height,
            isImage = true,
            onClick = onClick,
            isArticleSummary = isArticleSummary,
            isSaved = isSaved,
            onSave = onSave
        )
    }
}


@Composable
fun ArticleSummaryUI(
    article: ArticleEntity,
    height: Dp,
    isImage: Boolean = false,
    isArticleSummary: Boolean = true,
    onClick: (String) -> Unit = {},
    isSaved: Boolean = false,
    onSave: () -> Unit,
) {
    val unit = height / 6
    val modifier = if (isImage) {
        Modifier
    } else {
        Modifier
            .clip(RoundedCornerShape(16.dp))
            .shadow(
                elevation = 2.dp,
                spotColor = colorResource(id = R.color.text_secondary),
                ambientColor = colorResource(id = R.color.text_secondary)
            )
            .background(color = colorResource(id = R.color.white))
    }
    val infoModifier = if (isArticleSummary) {
        Modifier.height(unit * 2)
    } else {
        Modifier.heightIn(unit * 2)
    }
    Column(modifier = modifier) {
        Column(
            modifier = infoModifier
                .padding(10.dp)
        ) {
            TextMdRegular(text = article.sourceName, color = colorResource(id = R.color.black))
            Spacer(modifier = Modifier.height(8.dp))
            if (isArticleSummary) {
                TextSummary(
                    style = headingStyle(),
                    text = article.title
                )
            } else {
                Heading(text = article.title)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = colorResource(id = R.color.background))
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier
                .height(unit)
                .padding(10.dp)
        ) {
            if (isArticleSummary) {
                ReadMore { onClick(article.id) }
                Spacer(modifier = Modifier.width(8.dp))
                SaveArticle(isSaved = isSaved, onClick = onSave)
            } else {
                TextSmMedium(text = article.author)
            }
        }
    }
}
