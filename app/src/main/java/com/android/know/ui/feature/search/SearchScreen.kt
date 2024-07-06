package com.android.know.ui.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.know.R
import com.android.know.ui.components.SearchBar
import com.android.know.ui.components.text.Heading

@Composable
fun SearchScreen(
    searchData: SearchData,
    onSearchValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Heading(text = stringResource(id = R.string.search))
        Spacer(modifier = Modifier.height(12.dp))
        SearchBar(value = searchData.searchValue, onValueChange = onSearchValueChange, onSearch = { onSearch() })
        Column {

        }
    }
}
