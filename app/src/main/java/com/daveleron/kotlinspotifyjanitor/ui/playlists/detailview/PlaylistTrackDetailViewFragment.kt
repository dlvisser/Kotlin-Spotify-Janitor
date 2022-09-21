package com.daveleron.kotlinspotifyjanitor.ui.playlists.detailview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.daveleron.kotlinspotifyjanitor.databinding.FragmentPlaylistTrackDetailBinding

class PlaylistTrackDetailViewFragment : Fragment(){

    private val playlistTrackDetailViewModel: PlaylistTrackDetailViewModel by viewModels()
    private var _binding: FragmentPlaylistTrackDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistTrackDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = PlaylistTrackAdapter()
        initializeRecyclerView(adapter)
        initializeObservers(adapter)
        //playlistViewModel.createAuthTokenListener()

        return root
    }

    private fun initializeRecyclerView(requestItemAdapter : PlaylistTrackAdapter){

//        val swipeHandler = object : SwipeToDeleteGesture(){
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                topViewModel.deleteFromList(topViewModel.topTrackList.value!!.get(viewHolder.adapterPosition))
//                Snackbar.make(viewHolder.itemView,"Removed: ".plus(topViewModel.topTrackList.value!!.get(viewHolder.adapterPosition).songName),2000).show()
//            }
//        }

//        val itemTouchHelper = ItemTouchHelper(swipeHandler)

        binding.apply {
            rvPlayListTracks.apply {
                adapter = requestItemAdapter
                layoutManager = GridLayoutManager(requireContext(),2)
                setHasFixedSize(false)
            }
        }
    }


    private fun initializeObservers(itemAdapter : PlaylistTrackAdapter){
        playlistTrackDetailViewModel.currentPlaylistTracks.observe(viewLifecycleOwner, Observer {
            itemAdapter.submitList(it) })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}