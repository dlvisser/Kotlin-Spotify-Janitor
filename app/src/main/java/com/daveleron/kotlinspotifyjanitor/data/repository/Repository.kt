package com.daveleron.kotlinspotifyjanitor.data.repository

import com.daveleron.kotlinspotifyjanitor.data.api.RetrofitClient
import com.daveleron.kotlinspotifyjanitor.data.model.Artist
import com.daveleron.kotlinspotifyjanitor.data.model.TopList
import com.daveleron.kotlinspotifyjanitor.data.model.Track
import com.daveleron.kotlinspotifyjanitor.data.model.User
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.SearchObject
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists.PlayListObject
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists.PlayListRequestObject

object Repository {

    suspend fun getMyTopTracks(auth: String) : TopList{
        return RetrofitClient.apiService.getMyTopTracks(auth)
    }

    suspend fun getTrackById(auth: String, id:String) : Track{
        return RetrofitClient.apiService.getTrackById(auth,id)
    }

    suspend fun getArtists(auth: String) : List<Artist>{
        return RetrofitClient.apiService.getArtists(auth)
    }

    suspend fun getSearchResults(auth: String, query : String) : SearchObject{
        return RetrofitClient.apiService.getSearchResults(auth,query)
    }

    suspend fun getMyPlaylists(auth: String) : PlayListRequestObject {
        return RetrofitClient.apiService.getMyPlaylists(auth)
    }

    suspend fun getMyProfile(auth: String) : User{
        return RetrofitClient.apiService.getMyProfile(auth)
    }

    suspend fun getSpecificPlayList(auth: String, playlistId:String) : PlayListObject{
        return RetrofitClient.apiService.getSpecificPlaylist(auth,playlistId)
    }

    suspend fun getUserProfile(auth: String, userId : String) : User{
        return RetrofitClient.apiService.getUserProfile(auth,userId)
    }

    //    var job: CompletableJob? = null

//    fun getUserProfile( profileId : String, auth : String): LiveData<User>{
//        job = Job()
//        return object: LiveData<User>(){
//            override fun onActive() {
//                super.onActive()
//                job?.let { job ->
//                    CoroutineScope(IO + job).launch {
//                        val userProfile = RetrofitClient.apiService.getUserProfile(auth,profileId)
//                        withContext(Main){
//                            value = userProfile
//                            job.complete()
//                        }
//                    }
//                }
//            }
//        }
//    }

//    fun cancelJobs(){
//        job?.cancel()
//    }
}