package com.daveleron.kotlinspotifyjanitor.data.api

import kotlin.properties.Delegates

object TokenManager {

    var subscribedListeners = ArrayList<ChangeListener>()

    var AuthToken : String by Delegates.observable("initial value"){
        property, oldValue, newValue ->
            subscribedListeners.forEach{
                it.notifyTokenChanged(AuthToken)
            }
    }

    interface ChangeListener{
        fun notifyTokenChanged(token : String)
    }
}