package com.ighorosipov.testandroid.di

import com.ighorosipov.testandroid.domain.repository.CoinRepository
import com.ighorosipov.testandroid.domain.use_case.get_coin.GetCoinUseCase
import com.ighorosipov.testandroid.domain.use_case.get_coins.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface CoinModule {

    companion object {
        @Provides
        @Singleton
        fun getCoinsUseCase(
            repository: CoinRepository
        ): GetCoinsUseCase = GetCoinsUseCase(repository)

        @Provides
        @Singleton
        fun getCoinUseCase(
            repository: CoinRepository
        ): GetCoinUseCase = GetCoinUseCase(repository)
    }

}