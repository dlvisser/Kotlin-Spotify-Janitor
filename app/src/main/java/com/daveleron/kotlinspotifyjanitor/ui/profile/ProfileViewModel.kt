package com.daveleron.kotlinspotifyjanitor.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveleron.kotlinspotifyjanitor.data.api.TokenManager
import com.daveleron.kotlinspotifyjanitor.data.model.User
import com.daveleron.kotlinspotifyjanitor.data.repository.Repository

import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _userProfile = MutableLiveData<User>()
    val userProfile : LiveData<User> = _userProfile

    val spotifyAccountUri = "https://accounts.spotify.com"

    fun createAuthTokenListener(){
        TokenManager.subscribedListeners.add(object : TokenManager.ChangeListener {
            override fun notifyTokenChanged(token : String) {
                getCurrentUserProfile()
            }
        })
    }

    fun getCurrentUserProfile() {
        viewModelScope.launch {
            val userProfile = Repository.getMyProfile("Bearer ${TokenManager.AuthToken}")
            _userProfile.value = userProfile
        }
    }
}