//package com.daveleron.kotlinspotifyjanitor.viewmodel.recyclerview
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import coil.api.load
//import com.livewallgroup.spotifyjanitor.R
//import com.livewallgroup.spotifyjanitor.data.model.apiobjects.ItemsObject
//
//class RecyclerViewAdapter(list : Array<ItemsObject>) : RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder>() {
//
//    private var list : Array<ItemsObject> = list
//
//    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//        var itemTrackName : TextView
//        var itemTrackArtist : TextView
//        var itemTrackImage : ImageView
//
//        init {
//            itemTrackName = itemView.findViewById(R.id.tvPlaylistTitle)
//            itemTrackArtist = itemView.findViewById(R.id.tvTrackArtist)
//            itemTrackImage = itemView.findViewById(R.id.ivTrackImage)
//        }
//
//        fun bind(item : ItemsObject){
//            itemTrackName.text = item.songName
//            itemTrackArtist.text = item.artists.firstOrNull()?.name
//            itemTrackImage.load(item.album.images[0].imageUrl)
//        }
//    }
//
//    fun deleteItem(i : Int){
//        list.drop(i)
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.track_recyclerview_item,parent,false)
//        return ListViewHolder(v)
//    }
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        holder.bind(list[position])
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//}