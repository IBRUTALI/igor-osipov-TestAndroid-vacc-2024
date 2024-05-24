package com.ighorosipov.testandroid.presentation.screens.coin_list

import com.ighorosipov.testandroid.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
