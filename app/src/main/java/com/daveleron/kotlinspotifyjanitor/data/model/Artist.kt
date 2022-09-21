package com.daveleron.kotlinspotifyjanitor.data.model

import com.google.gson.annotations.SerializedName

data class Artist(

    @SerializedName("name")
    val name: String,

    @SerializedName("uri")
    val uri: String,

    @SerializedName("type")
    val type: String
){
    override fun toString(): String {
        return "Artist(name=$name, type=$type, uri=$uri"
    }
}

