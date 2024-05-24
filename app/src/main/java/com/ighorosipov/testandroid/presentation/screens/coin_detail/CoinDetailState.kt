package com.ighorosipov.testandroid.presentation.screens.coin_detail

import com.ighorosipov.testandroid.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)