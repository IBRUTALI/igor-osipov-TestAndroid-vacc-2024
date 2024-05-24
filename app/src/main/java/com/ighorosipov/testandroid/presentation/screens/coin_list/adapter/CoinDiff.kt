package com.ighorosipov.testandroid.presentation.screens.coin_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ighorosipov.testandroid.domain.model.Coin

class CoinDiff(
    private val oldList: List<Coin>,
    private val newList: List<Coin>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }

            oldList[oldItemPosition].isActive != newList[newItemPosition].isActive -> {
                false
            }

            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }

            oldList[oldItemPosition].rank != newList[newItemPosition].rank -> {
                false
            }

            oldList[oldItemPosition].symbol != newList[newItemPosition].symbol -> {
                false
            }

            else -> true
        }
    }
}