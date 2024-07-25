package com.example.practicesoft.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.practicesoft.Constants.BASE_URL
import com.example.practicesoft.api.ApiKeyInterceptor
import com.example.practicesoft.api.CountryService
import com.example.practicesoft.local.Player
import com.example.practicesoft.local.PlayerDAO
import com.example.practicesoft.local.PlayerDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


    @Provides
    @Singleton
    fun provideOkHttp(
        apiKeyInterceptor: ApiKeyInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(apiKeyInterceptor)
            .addNetworkInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    @Singleton
    fun provideService(okHttpClient: OkHttpClient): CountryService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
            .build().create(CountryService::class.java)
    }

    @Provides
    @Singleton
    fun createRoom(@ApplicationContext context: Context): PlayerDataBase {
        return Room.databaseBuilder(context, PlayerDataBase::class.java, "local_db").build()
    }

    @Provides
    @Singleton
    fun provideDAO(playerDataBase: PlayerDataBase): PlayerDAO {
        return playerDataBase.getDao()
    }
}