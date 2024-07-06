package com.android.know.data.mapper

import com.android.know.data.model.ArticleModel
import com.android.know.domain.entity.ArticleEntity
import com.android.know.domain.entity.SourceEntity

fun ArticleModel.toArticleEntity(index: Int): ArticleEntity {
    val sourceEntity = SourceEntity(
        id = this.source?.id.orEmpty(),
        name = this.source?.name.orEmpty()
    )

    return ArticleEntity(
        id = index.toString(),
        source = sourceEntity,
        title = title.orEmpty(),
        description = description.orEmpty(),
        author = author.orEmpty(),
        url = urlToImage.orEmpty()
    )
}