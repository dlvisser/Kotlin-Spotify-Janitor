package com.daveleron.kotlinspotifyjanitor.data.model.apiobjects

import com.google.gson.annotations.SerializedName

data class ImagesObject(

    @SerializedName("url")
    val imageUrl : String
)