package com.ighorosipov.testandroid.utills.di

import android.content.Context
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ighorosipov.testandroid.App
import com.ighorosipov.testandroid.di.AppComponent

fun Context.appComponent(): AppComponent {
        return when (this) {
            is App -> appComponent
            else -> this.applicationContext.appComponent()
        }
    }

    inline fun <reified T : ViewModel> AppCompatActivity.lazyViewModel(
        noinline create: (stateHandle: SavedStateHandle) -> T
    ) = viewModels<T> {
        ViewModelFactory(this, create)
    }

    inline fun <reified T : ViewModel> Fragment.lazyViewModel(
        noinline create: (stateHandle: SavedStateHandle) -> T
    ) = viewModels<T> {
        ViewModelFactory(this, create)
    }