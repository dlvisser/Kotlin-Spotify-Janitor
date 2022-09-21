package com.daveleron.kotlinspotifyjanitor.ui.search

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.daveleron.kotlinspotifyjanitor.CallbackReceiver
import com.daveleron.kotlinspotifyjanitor.MainActivity
import com.daveleron.kotlinspotifyjanitor.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), CallbackReceiver {

    private val searchViewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val item = SearchTrackListAdapter(this)
        initializeRecyclerView(item)
        initializeObservers(item)

        return root
    }

    private fun initializeRecyclerView(itemAdapter : SearchTrackListAdapter){

        binding.apply {
            rvSearchResultSongs.apply {
                adapter = itemAdapter
                layoutManager = GridLayoutManager(requireContext(),2)
                setHasFixedSize(false)
            }
        }
    }

    private fun initializeObservers(itemAdapter : SearchTrackListAdapter){
        searchViewModel.searchQuery.observe(viewLifecycleOwner, Observer {

            searchViewModel.getSearchResults(it)})
        val edt : EditText = binding.etSearchQuery
        edt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val test = binding.etSearchQuery.text
                Log.d("VALUE",test.toString())
                if (test.isEmpty()){return}
                searchViewModel.updateQuery(test)
               }

            override fun afterTextChanged(p0: Editable?) {}

        })

        searchViewModel.searchTrackList.observe(viewLifecycleOwner, Observer {
            itemAdapter.submitList(it) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onReceiveSpotifyUri(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            requireContext().startActivity(intent)
        } catch (e: ActivityNotFoundException){
            Snackbar.make(requireView(),"Spotify App Not Installed",5000).show()
        }

    }

    override fun onReceiveTrackDetailView(
        playlistName: String,
        playlistImage: String,
        playlistRequestUri: String
    ) {
        TODO("Not yet implemented")
    }
}