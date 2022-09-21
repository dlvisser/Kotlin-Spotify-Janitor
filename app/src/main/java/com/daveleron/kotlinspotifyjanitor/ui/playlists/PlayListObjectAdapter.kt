package com.daveleron.kotlinspotifyjanitor.ui.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists.PlayListRequestItemsObject
import com.daveleron.kotlinspotifyjanitor.databinding.PlaylistRecyclerviewItemBinding

class PlayListObjectAdapter() : ListAdapter<PlayListRequestItemsObject, PlayListObjectAdapter.PlayListItemsObjectViewHolder>(
    PlayListRequestItemsDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListItemsObjectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PlaylistRecyclerviewItemBinding.inflate(layoutInflater)
        return PlayListItemsObjectViewHolder(binding);
    }

    override fun onBindViewHolder(holder: PlayListItemsObjectViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            Snackbar.make(it,getItem(position).playlistName,1000).show()
        }
    }

    class PlayListItemsObjectViewHolder(private val binding: PlaylistRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PlayListRequestItemsObject){
            with(binding){
                tvPlaylistTitle.text = data.playlistName
                if(data.images.isNotEmpty()) {
                    ivTrackImage.load(data.images.first().imageUrl)
                }
            }
        }
    }

    class PlayListRequestItemsDiffCallback : DiffUtil.ItemCallback<PlayListRequestItemsObject>(){
        override fun areItemsTheSame(oldItem: PlayListRequestItemsObject, newItem: PlayListRequestItemsObject): Boolean {
            return oldItem.playlistName == newItem.playlistName
        }

        override fun areContentsTheSame(oldItem: PlayListRequestItemsObject, newItem: PlayListRequestItemsObject): Boolean {
            return areItemsTheSame(oldItem,newItem)
        }
    }
}


