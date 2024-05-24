package com.ighorosipov.testandroid.domain.use_case.get_coin

import com.ighorosipov.testandroid.domain.model.CoinDetail
import com.ighorosipov.testandroid.domain.repository.CoinRepository
import com.ighorosipov.testandroid.utills.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> {
        return flow {
            emit(Resource.Loading())
            emit(repository.getCoinsById(coinId))
        }
    }

}