package com.ighorosipov.testandroid.presentation.screens.coin_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ighorosipov.testandroid.domain.use_case.get_coin.GetCoinUseCase
import com.ighorosipov.testandroid.utills.Constants.PARAM_COIN_ID
import com.ighorosipov.testandroid.utills.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinDetailViewModel @AssistedInject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    @Assisted private val coinId: String?
): ViewModel() {

    private val _state = MutableLiveData<CoinDetailState>()
    val state: LiveData<CoinDetailState> = _state

    init {
        coinId?.let {
            getCoin(it)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    @AssistedFactory
    interface Factory {

        fun create(coinId: String): CoinDetailViewModel

    }

}