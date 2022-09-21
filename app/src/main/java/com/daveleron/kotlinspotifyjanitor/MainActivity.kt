package com.daveleron.kotlinspotifyjanitor

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.daveleron.kotlinspotifyjanitor.data.api.TokenManager
import com.daveleron.kotlinspotifyjanitor.databinding.ActivityMainBinding
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class MainActivity : AppCompatActivity(){

    private val clientId = "cef34d1f200a40c58f6388d2d03d2f72"
    private val clientSecret = "71486f887a44472589045cc5771cdcf5"
    private val scopes = arrayOf("user-read-recently-played","user-library-modify","user-read-email","user-read-private","user-top-read", "playlist-read-private","playlist-read-collaborative")
    private val redirectUri = "kotlinspotifyjanitor://callback"

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navView : NavigationView
    private lateinit var navController: NavController

    private lateinit var editor: SharedPreferences.Editor
    private lateinit var msharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buildUpUI()
        authenticateSpotify()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun buildUpUI(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        drawerLayout = binding.drawerLayout
        navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_tops
        ), drawerLayout)

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun authenticateSpotify() {
        val builder = AuthorizationRequest.Builder(clientId, AuthorizationResponse.Type.TOKEN, redirectUri)
        builder.setScopes(scopes)
        val request = builder.build()
        AuthorizationClient.openLoginInBrowser(this, request)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uri: Uri? = intent?.data

        if (uri != null){
            val response = AuthorizationResponse.fromUri(uri)

            when(response.type){
                AuthorizationResponse.Type.TOKEN ->{
                    Log.d("Auth Token",response.accessToken)
                    TokenManager.AuthToken = response.accessToken //SHOULD NOTIFY

                    editor = getSharedPreferences("SPOTIFY",0).edit()
                    editor.putString("token",response.accessToken)
                    editor.apply()
                }
                AuthorizationResponse.Type.ERROR -> {
                    Log.d("ERROR","SOMETHING HAS BROKEN")}
                else->
                    Log.d("NOTIFY","SOMETHING IS WRONG")

            }
        }
    }

    fun sharedPrefsInitialization(){
//        msharedPreferences = this.getSharedPreferences("SPOTIFY", 0)
//        val authCode : String? = msharedPreferences.getString("token","null")
//        authCode?.let {
//            viewModelFactory = MainViewModelFactory(authCode)
//        }
    }
}