package com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists

import com.google.gson.annotations.SerializedName
import com.daveleron.kotlinspotifyjanitor.data.model.Artist
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.AlbumObject
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.ImagesObject

data class PlayListObject (

    @SerializedName("collaborative")
    val collaborative : Boolean,

    @SerializedName("images")
    val images : ImagesObject,

    @SerializedName("name")
    val playListName : String,

    @SerializedName("tracks")
    val tracksListItemObject : TracksListItemObject
)

data class TracksListItemObject(

    @SerializedName("offset")
    val offset : Int,

    @SerializedName("limit")
    val limit : Int,

    @SerializedName("items")
    val tracksItemObject : List<TrackItemObject>

)

data class TrackItemObject(

    @SerializedName("track")
    val tracklist: TrackObject

)

data class TrackObject(

    @SerializedName("artists")
    val artistList : List<Artist>,

    @SerializedName("album")
    val albumObject : AlbumObject,

    @SerializedName("name")
    val songName: String
)