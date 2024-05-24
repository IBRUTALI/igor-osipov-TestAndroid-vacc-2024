package com.ighorosipov.testandroid.di

import android.app.Application
import com.ighorosipov.testandroid.presentation.MainActivity
import com.ighorosipov.testandroid.presentation.screens.coin_detail.CoinDetailViewModel
import com.ighorosipov.testandroid.presentation.screens.coin_list.CoinListViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(mainActivity: MainActivity)
    //fun inject(coinListFragment: CoinListFragment)
    //fun inject(coinDetailFragment: CoinDetailFragment)

    fun coinListViewModel(): CoinListViewModel.Factory
    fun coinDetailViewModel(): CoinDetailViewModel.Factory

}