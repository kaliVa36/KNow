package com.android.know

import com.android.know.data.datasource.NewsDataSource
import com.android.know.data.datasource.NewsDataSourceImpl
import com.android.know.data.repository.NewsRepositoryImpl
import com.android.know.data.service.NewsService
import com.android.know.domain.repository.NewsRepository
import com.android.know.domain.usecase.NewsUseCase
import com.android.know.ui.DummyViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.converter.gson.GsonConverterFactory

const val OKHTTP_CLIENT_TIMEOUT = 60L

val appModule = module {
    factory { KNowApplication() }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient().newBuilder()
            .addInterceptor(KNowInterceptor())
            .addInterceptor(interceptor)
            .connectTimeout(OKHTTP_CLIENT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OKHTTP_CLIENT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(OKHTTP_CLIENT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    single {
        val okHttpClient = get<OkHttpClient>()

        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org")
            .build()
    }

    single<NewsService> { get<Retrofit>().create(NewsService::class.java) }
    factory<NewsDataSource> { NewsDataSourceImpl(get()) }
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    factory { NewsUseCase(get()) }
    viewModel { DummyViewModel(newsUseCase = get()) }
}