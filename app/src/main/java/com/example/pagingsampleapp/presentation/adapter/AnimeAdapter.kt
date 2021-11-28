package com.example.pagingsampleapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsampleapp.R
import com.example.pagingsampleapp.data.vo.Anime
import com.example.pagingsampleapp.databinding.ViewAnimeTitleBinding
import com.example.pagingsampleapp.presentation.adapter.AnimeAdapter.AnimeViewHolder

class AnimeAdapter : PagingDataAdapter<Anime, AnimeViewHolder>(
    object : DiffUtil.ItemCallback<Anime>() {
        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.anime = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder =
        AnimeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_anime_title,
                parent,
                false
            )
        )

    class AnimeViewHolder(val binding: ViewAnimeTitleBinding) : RecyclerView.ViewHolder(binding.root)
}