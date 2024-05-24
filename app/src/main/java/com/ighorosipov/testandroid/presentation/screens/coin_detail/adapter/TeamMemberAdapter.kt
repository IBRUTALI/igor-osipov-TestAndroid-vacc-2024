package com.ighorosipov.testandroid.presentation.screens.coin_detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ighorosipov.testandroid.databinding.ItemTeamMemberBinding
import com.ighorosipov.testandroid.domain.model.TeamMember

class TeamMemberAdapter: RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder>() {

    private var teamMembers = emptyList<TeamMember>()

    class TeamMemberViewHolder(val binding: ItemTeamMemberBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberViewHolder {
        val binding = ItemTeamMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamMemberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return teamMembers.size
    }

    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
        holder.binding.apply {
            name.text = teamMembers[holder.adapterPosition].name
            this.position.text = teamMembers[holder.adapterPosition].position
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<TeamMember>) {
        teamMembers = newList
        notifyDataSetChanged()
    }
}