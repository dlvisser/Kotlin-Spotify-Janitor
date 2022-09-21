package com.daveleron.kotlinspotifyjanitor.data.model.apiobjects

import com.google.gson.annotations.SerializedName

data class SearchObject(

    @SerializedName("artists")
    val artists: ArtistsArraySearchObject,

    @SerializedName("tracks")
    val tracks: TracksArraySearchObject

)

data class ArtistsArraySearchObject(

    @SerializedName("items")
    val itemsObject: List<ItemsObject>

)

data class TracksArraySearchObject(

    @SerializedName("items")
    val itemsObjects: List<ItemsObject>

)