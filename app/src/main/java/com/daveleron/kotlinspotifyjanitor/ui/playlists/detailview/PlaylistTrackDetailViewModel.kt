package com.daveleron.kotlinspotifyjanitor.ui.playlists.detailview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveleron.kotlinspotifyjanitor.data.api.TokenManager
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists.TrackItemObject
import com.daveleron.kotlinspotifyjanitor.data.repository.Repository
import kotlinx.coroutines.launch

class PlaylistTrackDetailViewModel : ViewModel() {

    private val _currentPlaylistTracks = MutableLiveData<List<TrackItemObject>>()
    val currentPlaylistTracks : LiveData<List<TrackItemObject>> = _currentPlaylistTracks

//
//        fun createAuthTokenListener(){
//            TokenManager.subscribedListeners.add(object : TokenManager.ChangeListener {
//                override fun notifyTokenChanged(token: String) {
//                    getMyPlayLists()
//                }
//            })
//        }

    fun getSpecificTracks(playlistId : String){
        viewModelScope.launch {
            val playListObject = Repository.getSpecificPlayList("Bearer ${TokenManager.AuthToken}",playlistId)
            _currentPlaylistTracks.value = playListObject.tracksListItemObject.tracksItemObject
        }
    }
}
