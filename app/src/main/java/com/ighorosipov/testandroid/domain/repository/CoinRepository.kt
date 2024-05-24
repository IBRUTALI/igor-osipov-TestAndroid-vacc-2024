package com.ighorosipov.testandroid.domain.repository

import com.ighorosipov.testandroid.domain.model.Coin
import com.ighorosipov.testandroid.domain.model.CoinDetail
import com.ighorosipov.testandroid.utills.Resource

interface CoinRepository {

    suspend fun getCoins(): Resource<List<Coin>>

    suspend fun getCoinsById(coinId: String): Resource<CoinDetail>

}