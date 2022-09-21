package com.daveleron.kotlinspotifyjanitor.ui.playlists.detailview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.daveleron.kotlinspotifyjanitor.data.model.Artist
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists.TrackItemObject
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists.TrackObject
import com.daveleron.kotlinspotifyjanitor.databinding.TrackRecyclerviewItemBinding

class PlaylistTrackAdapter : ListAdapter<TrackItemObject, PlaylistTrackAdapter.PlayListTrackViewHolder>(
    PlayListTrackItemsDiffCallback()){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListTrackViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = TrackRecyclerviewItemBinding.inflate(layoutInflater)
            return PlayListTrackViewHolder(binding);
        }

        override fun onBindViewHolder(holder: PlayListTrackViewHolder, position: Int) {
            holder.bind(getItem(position).tracklist)
            holder.itemView.setOnClickListener {
            }
        }

        class PlayListTrackViewHolder(private val binding: TrackRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(data: TrackObject){
                with(binding){
                    tvTrackTitle.text = data.songName
                    tvTrackArtist.text = getAllArtists(data.artistList)
                    if(data.albumObject.images.isNotEmpty()) {
                        ivTrackImage.load(data.albumObject.images.first().imageUrl)
                    }
                }
            }

            private fun getAllArtists(artists : List<Artist>) : String{
                val builder = StringBuilder()
                val integer = 1
                artists.forEach {
                    if (artists.size.equals(integer)){
                        builder.append(it.name)
                    }
                    else{
                        builder.append(it.name.plus(","))
                    }
                    integer.inc()
                }
                return builder.toString()
            }
        }

        class PlayListTrackItemsDiffCallback : DiffUtil.ItemCallback<TrackItemObject>(){
            override fun areItemsTheSame(oldItem: TrackItemObject, newItem: TrackItemObject): Boolean {
                return oldItem.tracklist.songName == newItem.tracklist.songName
            }

            override fun areContentsTheSame(oldItem: TrackItemObject, newItem: TrackItemObject): Boolean {
                return areItemsTheSame(oldItem,newItem)
            }
        }
    }