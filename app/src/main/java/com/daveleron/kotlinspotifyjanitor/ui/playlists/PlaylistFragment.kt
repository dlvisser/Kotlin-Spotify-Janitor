package com.daveleron.kotlinspotifyjanitor.ui.playlists

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.daveleron.kotlinspotifyjanitor.CallbackReceiver
import com.daveleron.kotlinspotifyjanitor.R
import com.daveleron.kotlinspotifyjanitor.databinding.FragmentPlaylistsBinding

class PlaylistFragment : Fragment(), CallbackReceiver {

    private val playlistViewModel: PlaylistViewModel by viewModels()
    private var _binding: FragmentPlaylistsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = PlayListObjectAdapter()
        initializeRecyclerView(adapter)
        initializeObservers(adapter)
        enableSwipeRefresh()
        //playlistViewModel.createAuthTokenListener()

        return root
    }

    private fun initializeRecyclerView(requestItemAdapter : PlayListObjectAdapter){

//        val swipeHandler = object : SwipeToDeleteGesture(){
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                topViewModel.deleteFromList(topViewModel.topTrackList.value!!.get(viewHolder.adapterPosition))
//                Snackbar.make(viewHolder.itemView,"Removed: ".plus(topViewModel.topTrackList.value!!.get(viewHolder.adapterPosition).songName),2000).show()
//            }
//        }

//        val itemTouchHelper = ItemTouchHelper(swipeHandler)

        binding.apply {
            rvCurrentPlayLists.apply {
                adapter = requestItemAdapter
                layoutManager = GridLayoutManager(requireContext(),2)
                setHasFixedSize(false)
            }
        }
    }


    private fun initializeObservers(itemAdapter : PlayListObjectAdapter){
        playlistViewModel.playlistList.observe(viewLifecycleOwner, Observer {
            itemAdapter.submitList(it) })
    }

    fun enableSwipeRefresh(){
        binding.refreshView.setOnRefreshListener {
            playlistViewModel.getMyPlayLists()
            binding.refreshView.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        playlistViewModel.getMyPlayLists()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onReceiveSpotifyUri(uri: Uri) {
        TODO("Not yet implemented")
    }

    override fun onReceiveTrackDetailView(
        playlistName: String,
        playlistImage: String,
        playlistRequestUri: String
    ){
        findNavController().navigate(R.id.next_action)
    }
}