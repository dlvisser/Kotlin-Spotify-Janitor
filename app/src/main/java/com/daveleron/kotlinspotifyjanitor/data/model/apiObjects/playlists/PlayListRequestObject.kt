package com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists

import com.google.gson.annotations.SerializedName
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.ImagesObject

data class PlayListRequestObject(

    @SerializedName("items")
    val playListItems : List<PlayListRequestItemsObject>

)

data class PlayListRequestItemsObject(

    @SerializedName("name")
    val playlistName : String,

    @SerializedName("description")
    val description : String,

    @SerializedName("images")
    val images : List<ImagesObject>,

    @SerializedName("tracks")
    val tracks : PlayListRequestTracksItemObject
)

data class PlayListRequestTracksItemObject(

    @SerializedName("href")
    val requestUrl: String,

    @SerializedName("total")
    val amountOfSongs: Int

)
