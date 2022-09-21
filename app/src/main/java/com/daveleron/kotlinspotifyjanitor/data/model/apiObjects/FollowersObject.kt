package com.daveleron.kotlinspotifyjanitor.data.model.apiobjects

import com.google.gson.annotations.SerializedName

data class FollowersObject(
    @SerializedName("total")
    val amountOfFollowers : Int
)