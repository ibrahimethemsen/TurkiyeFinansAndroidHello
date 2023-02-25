package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentLibraryBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.adapter.LibraryAdapter
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.booksList
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.favoriteList

class LibraryFragment : Fragment() {
    private var _binding : FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private val libraryAdapter = LibraryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        listener()
    }
    private fun listener(){
        libraryAdapter.setClickBook(::clickBook)
    }
    private fun setAdapter(){
        libraryAdapter.updateList(booksList)
        binding.libraryRv.adapter = libraryAdapter
    }
    private fun clickBook(id : Int){
        if (booksList[id].isFavorite){
            booksList[id] = booksList[id].copy(isFavorite = false)
        }else{
            booksList[id] = booksList[id].copy(isFavorite = true)
        }
        favoriteList.add(booksList[id])
    }
}