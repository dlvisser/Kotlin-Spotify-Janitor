package com.daveleron.kotlinspotifyjanitor.data.model

import com.google.gson.annotations.SerializedName

data class Playlist(

    @SerializedName("name")
    val name : String,

    @SerializedName("trackcount")
    val trackCount : Int,

    @SerializedName("tracks")
    val tracks : List<Track>
){
    override fun toString(): String {
        return "Playlist(name=$name, trackCount=$trackCount, tracks=$tracks"
    }
}
