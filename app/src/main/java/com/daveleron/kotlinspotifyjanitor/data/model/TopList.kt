package com.daveleron.kotlinspotifyjanitor.data.model

import com.google.gson.annotations.SerializedName
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.ItemsObject

data class TopList(

    @SerializedName("items")
    val items: List<ItemsObject>

){
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}