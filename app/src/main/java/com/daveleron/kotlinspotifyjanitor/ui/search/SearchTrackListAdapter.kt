package com.daveleron.kotlinspotifyjanitor.ui.search

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.daveleron.kotlinspotifyjanitor.CallbackReceiver
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.ItemsObject
import com.daveleron.kotlinspotifyjanitor.databinding.SearchTracklistRecyclerviewItemBinding

class SearchTrackListAdapter(callbackReceiver: CallbackReceiver) : ListAdapter<ItemsObject, SearchTrackListAdapter.ItemsObjectViewHolder>(
    ItemsObjectDiffCallback()
){

    private var callbackReceiver : CallbackReceiver = callbackReceiver

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsObjectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SearchTracklistRecyclerviewItemBinding.inflate(layoutInflater)
        return ItemsObjectViewHolder(binding);
    }

    override fun onBindViewHolder(holder: ItemsObjectViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            val uri = Uri.parse(getItem(position).spotifyRedirectUri)
            callbackReceiver.onReceiveSpotifyUri(uri)
        }
    }

    class ItemsObjectViewHolder(private val binding: SearchTracklistRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ItemsObject){
            with(binding){
                tvTrackTitle.text = data.songName
                tvTrackArtist.text = data.artists.first().name
                ivTrackImage.load(data.album.images.first().imageUrl)
            }
        }
    }

    class ItemsObjectDiffCallback : DiffUtil.ItemCallback<ItemsObject>(){
        override fun areItemsTheSame(oldItem: ItemsObject, newItem: ItemsObject): Boolean {
            return oldItem.songName == newItem.songName
        }

        override fun areContentsTheSame(oldItem: ItemsObject, newItem: ItemsObject): Boolean {
            return areItemsTheSame(oldItem,newItem)
        }
    }
}


