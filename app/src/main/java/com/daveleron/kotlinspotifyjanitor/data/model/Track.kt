package com.daveleron.kotlinspotifyjanitor.data.model

import com.google.gson.annotations.SerializedName
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.AlbumObject

data class Track(

    @SerializedName("name")
    val name: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("duration_ms")
    val duration: Int,

    @SerializedName("album")
    val album: AlbumObject
){
    override fun toString(): String {
//        return "Track(title=$name, albumType=$albumType, duration=$duration, album=$album"
        return "Track(title=$name, albumType=$type, duration=$duration"
    }
}
