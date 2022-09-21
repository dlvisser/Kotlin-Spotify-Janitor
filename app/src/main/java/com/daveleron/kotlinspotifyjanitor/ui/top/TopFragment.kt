package com.daveleron.kotlinspotifyjanitor.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.daveleron.kotlinspotifyjanitor.databinding.FragmentTopBinding
import com.daveleron.kotlinspotifyjanitor.viewmodel.recyclerview.SwipeToDeleteGesture

class TopFragment : Fragment() {

    private val topViewModel: TopViewModel by viewModels()
    private var _binding: FragmentTopBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val item = ItemsObjectAdapter()
        initializeRecyclerView(item)
        initializeObservers(item)
        enableSwipeRefresh()
        topViewModel.createAuthTokenListener()

        return root
    }

    private fun initializeRecyclerView(itemAdapter : ItemsObjectAdapter){

        val swipeHandler = object : SwipeToDeleteGesture(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                topViewModel.deleteFromList(topViewModel.topTrackList.value!!.get(viewHolder.adapterPosition))
                Snackbar.make(viewHolder.itemView,"Removed: ".plus(topViewModel.topTrackList.value!!.get(viewHolder.adapterPosition).songName),2000).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)

        binding.apply {
            rvTopTracks.apply {
                adapter = itemAdapter
                layoutManager = LinearLayoutManager(requireContext())
                itemTouchHelper.attachToRecyclerView(this)
                setHasFixedSize(false)
            }
        }
    }


    private fun initializeObservers(itemAdapter : ItemsObjectAdapter){
        topViewModel.topTrackList.observe(viewLifecycleOwner, Observer {
            itemAdapter.submitList(it) })
    }

    fun enableSwipeRefresh(){
        binding.refreshView.setOnRefreshListener {
            topViewModel.getMyTopTracks()
//            Snackbar.make(binding, "Recoverd data",2000).show()
            binding.refreshView.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}