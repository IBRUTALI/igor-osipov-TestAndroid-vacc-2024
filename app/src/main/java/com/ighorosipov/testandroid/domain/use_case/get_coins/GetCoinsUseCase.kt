package com.ighorosipov.testandroid.domain.use_case.get_coins

import com.ighorosipov.testandroid.domain.model.Coin
import com.ighorosipov.testandroid.domain.repository.CoinRepository
import com.ighorosipov.testandroid.utills.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> {
        return flow {
            emit(Resource.Loading())
            emit(repository.getCoins())
        }
    }

}