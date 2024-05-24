package com.ighorosipov.testandroid.di

import com.ighorosipov.testandroid.data.remote.CoinApi
import com.ighorosipov.testandroid.utills.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
interface NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideMarketService(): CoinApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }

    }

}