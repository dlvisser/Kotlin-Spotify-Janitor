package com.daveleron.kotlinspotifyjanitor.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveleron.kotlinspotifyjanitor.data.api.TokenManager
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.playlists.PlayListRequestItemsObject
import com.daveleron.kotlinspotifyjanitor.data.repository.Repository
import kotlinx.coroutines.launch

class PlaylistViewModel : ViewModel(){

    private val _playlistList = MutableLiveData<List<PlayListRequestItemsObject>>()
    val playlistList : LiveData<List<PlayListRequestItemsObject>> = _playlistList

    fun createAuthTokenListener(){
        TokenManager.subscribedListeners.add(object : TokenManager.ChangeListener {
            override fun notifyTokenChanged(token: String) {
                getMyPlayLists()
            }
        })
    }

    fun deleteFromList(itemsObject : PlayListRequestItemsObject){
        viewModelScope.launch {
            val test = _playlistList.value?.toMutableList()
            test?.remove(itemsObject)
            _playlistList.postValue(test!!)
        }
    }

    fun getMyPlayLists(){
        viewModelScope.launch {
            val playListRequestObject = Repository.getMyPlaylists("Bearer ${TokenManager.AuthToken}")
            _playlistList.value = playListRequestObject.playListItems
        }
    }
}
