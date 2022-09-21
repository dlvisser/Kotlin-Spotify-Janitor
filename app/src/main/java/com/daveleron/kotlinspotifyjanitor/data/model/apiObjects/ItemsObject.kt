package com.daveleron.kotlinspotifyjanitor.data.model.apiobjects

import com.google.gson.annotations.SerializedName
import com.daveleron.kotlinspotifyjanitor.data.model.Artist

data class ItemsObject(

    @SerializedName("artists")
    val artists : List<Artist>,

    @SerializedName("name")
    val songName : String,

    @SerializedName("album")
    val album : AlbumObject,

    @SerializedName("uri")
    val spotifyRedirectUri : String


) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}