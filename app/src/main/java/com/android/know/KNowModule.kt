package com.android.know

import android.app.Application
import androidx.room.Room
import com.android.know.data.BASE_URL
import com.android.know.data.dao.NewsDao
import com.android.know.data.database.NewsDatabase
import com.android.know.data.datasource.NewsDataSource
import com.android.know.data.datasource.NewsDataSourceImpl
import com.android.know.data.repository.NewsRepositoryImpl
import com.android.know.data.service.NewsService
import com.android.know.domain.repository.NewsRepository
import com.android.know.domain.usecase.ArticleByIdUseCase
import com.android.know.domain.usecase.NewsUseCase
import com.android.know.domain.usecase.TopHeadlinesUseCase
import com.android.know.ui.feature.article.ArticleViewModel
import com.android.know.ui.feature.article.SavedArticleViewModel
import com.android.know.ui.feature.home.HomeViewModel
import com.android.know.ui.feature.saved.SavedArticlesViewModel
import com.android.know.ui.feature.search.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
            .baseUrl(BASE_URL)
            .build()
    }
    single<NewsService> { get<Retrofit>().create(NewsService::class.java) }
    factory<NewsDataSource> { NewsDataSourceImpl(get()) }
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    factory { NewsUseCase(get()) }
    factory { TopHeadlinesUseCase(get()) }
    factory { ArticleByIdUseCase(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ArticleViewModel(get()) }
}

fun provideDataBase(application: Application): NewsDatabase =
    Room.databaseBuilder(
        application,
        NewsDatabase::class.java,
        "table_post"
    ).
    fallbackToDestructiveMigration().build()

fun provideDao(postDataBase: NewsDatabase): NewsDao = postDataBase.dao


val dataBaseModule = module {
    single { provideDataBase(get()) }
    single { provideDao(get()) }
}

val newsViewModel = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { SavedArticleViewModel(get()) }
    viewModel { SavedArticlesViewModel(get()) }
}