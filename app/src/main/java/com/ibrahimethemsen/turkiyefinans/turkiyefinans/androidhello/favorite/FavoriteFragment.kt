package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentFavoriteBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.adapter.LibraryAdapter
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.data.favoriteList

class FavoriteFragment : Fragment() {
    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val libraryAdapter = LibraryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }
    private fun setAdapter(){
        libraryAdapter.updateList(favoriteList.distinct())
        binding.favoriteRv.adapter = libraryAdapter
    }
}