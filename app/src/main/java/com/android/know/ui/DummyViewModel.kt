package com.android.know.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.know.domain.usecase.NewsUseCase
import com.android.know.domain.usecase.TopHeadlinesUseCase
import kotlinx.coroutines.launch

class DummyViewModel(private val newsUseCase: NewsUseCase, private val topHeadlinesUseCase: TopHeadlinesUseCase) : ViewModel() {
    init {
        viewModelScope.launch {
            newsUseCase().fold(
                onSuccess = { Log.d("NEWS", it.toString()) },
                onFailure = { Log.d("NEWS", it.message ?: "") }
            )
            // Top headlines test
            topHeadlinesUseCase("").fold(
                onSuccess = { Log.d("TOP-HEADLINES", it.toString()) },
                onFailure = { Log.d("TOP-HEADLINES", it.message ?: "") }
            )
        }
    }
}