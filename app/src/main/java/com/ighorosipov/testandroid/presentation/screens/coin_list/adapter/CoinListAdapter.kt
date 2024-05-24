package com.ighorosipov.testandroid.presentation.screens.coin_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ighorosipov.testandroid.R
import com.ighorosipov.testandroid.databinding.ItemCoinBinding
import com.ighorosipov.testandroid.domain.model.Coin

class CoinListAdapter : RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {
    private var onClickListener: OnClickListener? = null
    private var coins = emptyList<Coin>()

    class CoinListViewHolder(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        holder.binding.apply {
            "${coins[holder.adapterPosition].rank}.".apply {
                number.text = this
            }
            "${coins[holder.adapterPosition].name} (${coins[holder.adapterPosition].symbol})".apply {
                title.text = this
            }
            if (coins[holder.adapterPosition].isActive) {
                status.text = holder.itemView.context.getString(R.string.active)
                status.setTextColor(holder.itemView.context.getColor(R.color.text_green))
            } else {
                status.text = holder.itemView.context.getString(R.string.inactive)
                status.setTextColor(holder.itemView.context.getColor(R.color.text_red))
            }
        }
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener?.onCoinClick(holder.adapterPosition, coins[holder.adapterPosition])
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onCoinClick(position: Int, coin: Coin)
    }

    fun setList(newList: List<Coin>) {
        val diffUtil = CoinDiff(coins, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        diffResult.dispatchUpdatesTo(this)
        coins = newList
    }
}