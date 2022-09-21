package com.daveleron.kotlinspotifyjanitor.data.model

import com.google.gson.annotations.SerializedName
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.FollowersObject
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.ImagesObject

data class User(

    @SerializedName("id")
    val id: String,

    @SerializedName("display_name")
    val displayName : String,

    @SerializedName("email")
    val email : String,

    @SerializedName("images")
    val imagesObject: List<ImagesObject>,

    @SerializedName("product")
    val product: String,

    @SerializedName("country")
    val country : String,

    @SerializedName("followers")
    val followersObject: FollowersObject
){
    override fun toString(): String {
        return "User(id=$id, email=$email, product=$product, country=$country, totalFollowers=${followersObject.amountOfFollowers})"
    }
}
