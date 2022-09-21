package com.daveleron.kotlinspotifyjanitor.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.daveleron.kotlinspotifyjanitor.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private val profileViewModel : ProfileViewModel by viewModels()
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        profileViewModel.getCurrentUserProfile()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        createProfileObservers()
        enableButtonClicks()
        profileViewModel.createAuthTokenListener()

        return root
    }

    private fun createProfileObservers(){

        val tvProfileName: TextView = binding.tvProfileName
        val tvProfileEmail : TextView = binding.tvProfileEmail
        val tvProfileCountry : TextView = binding.tvProfileCountry
        val tvProfileProduct : TextView = binding.tvProfileProduct
        val ivProfilePicture : ImageView = binding.ivProfilePicture

        profileViewModel.userProfile.observe(viewLifecycleOwner, Observer {
            tvProfileName.text = it.displayName
            tvProfileEmail.text = it.email
            tvProfileCountry.text = it.country
            tvProfileProduct.text = it.product
            ivProfilePicture.load(it.imagesObject.first().imageUrl)
        })
    }

    private fun enableButtonClicks(){
        binding.btnProfileLogout.setOnClickListener{
            Snackbar.make(it,"HELLO YOU PRESSED LOGOUT",1000).show()
            val uri = Uri.parse(profileViewModel.spotifyAccountUri)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            requireContext().startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}