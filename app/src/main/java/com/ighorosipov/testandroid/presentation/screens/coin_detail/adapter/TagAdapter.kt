package com.ighorosipov.testandroid.presentation.screens.coin_detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ighorosipov.testandroid.databinding.ItemTagBinding

class TagAdapter: RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    private var tags = emptyList<String>()

    class TagViewHolder(val binding: ItemTagBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.binding.apply {
            title.text = tags[holder.adapterPosition]
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<String>) {
        tags = newList
        notifyDataSetChanged()
    }

}