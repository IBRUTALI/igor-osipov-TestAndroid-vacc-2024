package com.ighorosipov.testandroid.di

import com.ighorosipov.testandroid.data.repository.CoinRepositoryImpl
import com.ighorosipov.testandroid.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindCoinRepository(coinRepository: CoinRepositoryImpl): CoinRepository

}