package com.daveleron.kotlinspotifyjanitor.data.model.apiobjects

import com.google.gson.annotations.SerializedName

data class AlbumObject(

    @SerializedName("images")
    val images: List<ImagesObject>

)