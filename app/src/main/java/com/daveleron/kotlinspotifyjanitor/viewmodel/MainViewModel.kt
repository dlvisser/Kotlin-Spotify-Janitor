//package com.daveleron.kotlinspotifyjanitor.viewmodel
//
//import androidx.lifecycle.*
//import com.livewallgroup.spotifyjanitor.data.model.TopList
//import com.livewallgroup.spotifyjanitor.data.model.Track
//import com.livewallgroup.spotifyjanitor.data.model.User
//import com.livewallgroup.spotifyjanitor.data.model.apiObjects.ItemsObject
//import com.livewallgroup.spotifyjanitor.data.repository.Repository
//import kotlinx.coroutines.launch
//
//class MainViewModel() : ViewModel(){
//
//    val singleTrack : MutableLiveData<Track> = MutableLiveData()
//    val topTrackList : MutableLiveData<List<ItemsObject>> = MutableLiveData()
//    val userAccount : MutableLiveData<User> = MutableLiveData()
//
////    private var authCode: String
//
//    fun initializeWithAuthToken(token : String){
////        getTrack(token,"6y6xhAgZjvxy5kR5rigpY3")
////        getMyUserProfile(token)
//        getMyTopTracks(token)
//    }
//
//    fun getTrack(token: String, id: String){
//        viewModelScope.launch {
//            val response : Track = Repository.getTrackById("Bearer $token",id)
//            singleTrack.value = response
//        }
//    }
//
//    fun getMyTopTracks(token: String){
//        viewModelScope.launch {
//            val response : TopList = Repository.getMyTopTracks("Bearer $token")
//            topTrackList.value = response.items
//        }
//    }
//
//    fun getMyUserProfile(token: String){
//        viewModelScope.launch {
//            val response : User = Repository.getMyProfile("Bearer $token")
//            userAccount.value = response
//        }
//    }
//}
