package com.daveleron.kotlinspotifyjanitor.data.api

import com.daveleron.kotlinspotifyjanitor.data.model.Artist
import com.daveleron.kotlinspotifyjanitor.data.model.TopList
import com.daveleron.kotlinspotifyjanitor.data.model.Track
import com.daveleron.kotlinspotifyjanitor.data.model.User
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.SearchObject
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists.PlayListObject
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists.PlayListRequestObject
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("tracks")
    suspend fun getTracks(@Header("Authorization") authorization:String, @Query("?Ids={Ids}") Ids : String) : List<Track>

//    @GET("search?type=track%2Cartist")
//    suspend fun getSearchResults(@Header("Authorization")authorization: String, @Query("?q={query}&offset={offset}") query : String, offset : Int) : SearchObject
    @GET("search?type=track")
    suspend fun getSearchResults(@Header("Authorization")authorization: String, @Query("q") query : String) : SearchObject

    @GET("tracks/{Id}")
    suspend fun getTrackById(@Header("Authorization")authorization: String, @Path("Id") Id : String) : Track

    @GET("artists")
    suspend fun getArtists(@Header("Authorization") authorization:String) : List<Artist>

    @GET("me/playlists")
    suspend fun getMyPlaylists(@Header("Authorization")authorization: String) : PlayListRequestObject

    @GET("playlists/{playlist_id}")
    suspend fun getSpecificPlaylist(@Header("Authorization")authorization: String, @Path("playlist_id")playlistId : String) : PlayListObject

    @GET("me")
    suspend fun getMyProfile(@Header("Authorization")authorization: String) : User

    @GET("me/top/tracks")
    suspend fun getMyTopTracks(@Header("Authorization")authorization: String) : TopList

    @GET("users/{userId}")
    suspend fun getUserProfile(@Header("Authorization")authorization: String, @Path("userId") userId : String) : User
}