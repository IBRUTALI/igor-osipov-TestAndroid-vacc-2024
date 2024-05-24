package com.ighorosipov.testandroid.data.repository

import com.ighorosipov.testandroid.data.mapper.CoinMapper
import com.ighorosipov.testandroid.data.remote.CoinApi
import com.ighorosipov.testandroid.domain.model.Coin
import com.ighorosipov.testandroid.domain.model.CoinDetail
import com.ighorosipov.testandroid.domain.repository.CoinRepository
import com.ighorosipov.testandroid.utills.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
): CoinRepository {
    override suspend fun getCoins(): Resource<List<Coin>> {
        return try {
            Resource.Loading(null)
            val coins = api.getCoins().map { CoinMapper().coinDtoToCoin(it) }
            Resource.Success(coins)
        } catch (e: HttpException) {
            Resource.Error("Couldn't reach server. Check your internet connection")
        } catch (e: IOException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        }
    }

    override suspend fun getCoinsById(coinId: String): Resource<CoinDetail> {
        return try {
            Resource.Loading(null)
            val coin = CoinMapper().coinDetailDtoToCoinDetail(api.getCoinById(coinId))
            Resource.Success(coin)
        } catch (e: HttpException) {
            Resource.Error("Couldn't reach server. Check your internet connection")
        } catch (e: IOException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        }
    }
}