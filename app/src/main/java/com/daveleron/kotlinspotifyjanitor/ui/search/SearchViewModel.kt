package com.daveleron.kotlinspotifyjanitor.ui.search

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveleron.kotlinspotifyjanitor.data.api.TokenManager
import com.daveleron.kotlinspotifyjanitor.data.model.apiobjects.ItemsObject
import com.daveleron.kotlinspotifyjanitor.data.repository.Repository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _searchTrackList = MutableLiveData<List<ItemsObject>>()
    val searchTrackList : LiveData<List<ItemsObject>> = _searchTrackList

//    private val _searchArtistList = MutableLiveData<List<ItemsObject>>()
//    val searchArtistList : LiveData<List<ItemsObject>> = _searchArtistList

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery : LiveData<String> = _searchQuery


    fun getSearchResults(test : String){
        viewModelScope.launch {
            val searchTrackList = Repository.getSearchResults("Bearer ${TokenManager.AuthToken}", test)
//            _searchArtistList.value = searchTrackList.artists.itemsObject
            _searchTrackList.value = searchTrackList.tracks.itemsObjects
        }
    }

    fun updateQuery(string: Editable){
        _searchQuery.postValue(string.toString())
    }

}