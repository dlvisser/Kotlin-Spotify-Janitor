package com.daveleron.kotlinspotifyjanitor

import android.net.Uri

interface CallbackReceiver {
    fun onReceiveSpotifyUri(uri : Uri)
    fun onReceiveTrackDetailView(playlistName: String, playlistImage: String, playlistRequestUri : String)
}