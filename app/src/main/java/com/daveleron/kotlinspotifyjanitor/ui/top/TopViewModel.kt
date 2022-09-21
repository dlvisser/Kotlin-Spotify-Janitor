package com.daveleron.kotlinspotifyjanitor.ui.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveleron.kotlinspotifyjanitor.data.api.TokenManager
import com.daveleron.kotlinspotifyjanitor.data.api.TokenManager.ChangeListener
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.ItemsObject
import com.daveleron.kotlinspotifyjanitor.data.repository.Repository
import kotlinx.coroutines.launch

class TopViewModel : ViewModel() {


    private val _topTrackList = MutableLiveData<List<ItemsObject>>()

    val topTrackList : LiveData<List<ItemsObject>> = _topTrackList

    fun createAuthTokenListener(){
        TokenManager.subscribedListeners.add(object : ChangeListener{
            override fun notifyTokenChanged(token: String) {
                getMyTopTracks()
            }
        })
    }

    fun deleteFromList(itemsObject : ItemsObject){
        viewModelScope.launch {
            val test = _topTrackList.value?.toMutableList()
            test?.remove(itemsObject)
            _topTrackList.postValue(test!!)
        }
    }

    fun getMyTopTracks(){
        viewModelScope.launch {
            val topTrackList = Repository.getMyTopTracks("Bearer ${TokenManager.AuthToken}")
            _topTrackList.value = topTrackList.items
        }
    }
}