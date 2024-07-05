package com.android.know.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.know.domain.usecase.NewsUseCase
import kotlinx.coroutines.launch

class DummyViewModel(private val newsUseCase: NewsUseCase): ViewModel() {
    init {
        viewModelScope.launch {
            newsUseCase().fold(
                onSuccess = { Log.d("NEWS", it.toString() ) },
                onFailure = { Log.d("NEWS", it.message?: "" ) }
            )
        }
    }
}