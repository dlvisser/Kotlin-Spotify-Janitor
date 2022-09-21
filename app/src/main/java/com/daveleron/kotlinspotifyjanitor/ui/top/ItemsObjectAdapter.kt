package com.daveleron.kotlinspotifyjanitor.ui.top

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.daveleron.kotlinspotifyjanitor.data.model.Artist
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.ItemsObject
import com.daveleron.kotlinspotifyjanitor.databinding.TrackRecyclerviewItemBinding

class ItemsObjectAdapter : ListAdapter<ItemsObject, ItemsObjectAdapter.ItemsObjectViewHolder>(
    ItemsObjectDiffCallback()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsObjectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TrackRecyclerviewItemBinding.inflate(layoutInflater)
        return ItemsObjectViewHolder(binding);
    }

    override fun onBindViewHolder(holder: ItemsObjectViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            Snackbar.make(it,getItem(position).artists.first().name,1000).show()
        }
    }

    class ItemsObjectViewHolder(private val binding: TrackRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ItemsObject){
            with(binding){
                tvTrackTitle.text = data.songName
                tvTrackArtist.text = getAllArtists(data.artists)
                ivTrackImage.load(data.album.images.first().imageUrl)
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



    class ItemsObjectDiffCallback : DiffUtil.ItemCallback<ItemsObject>(){
        override fun areItemsTheSame(oldItem: ItemsObject, newItem: ItemsObject): Boolean {
            return oldItem.songName == newItem.songName
        }

        override fun areContentsTheSame(oldItem: ItemsObject, newItem: ItemsObject): Boolean {
            return areItemsTheSame(oldItem,newItem)
        }
    }
}


