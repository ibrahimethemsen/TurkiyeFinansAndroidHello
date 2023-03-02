package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnCloseListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.adapter.LibraryAdapter
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentLibraryBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.component.Category
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.library.data.booksList
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.library.data.favoriteList
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.Constants.START_LIBRARY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibraryFragment : Fragment() {
    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private val libraryAdapter = LibraryAdapter()
    private val viewModel by viewModels<LibraryViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        listener()
        setRecyclerList()
        searchView()
    }
    private fun searchView(){
        binding.librarySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,OnCloseListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                queryNameList(newText)
                return true
            }

            override fun onClose(): Boolean {
                setRecyclerList()
                return true
            }

        })
    }

    private fun queryNameList(query : String?){
        if (query.isNullOrEmpty()){
            setRecyclerList()
        }else{
            booksList.filter {
                it.name.lowercase() == query.lowercase()
            }.also {
                libraryAdapter.updateList(it)
            }
        }
    }

    private fun listener() {
        libraryAdapter.setClickBook(::clickBook)
        binding.libraryCategoryGroup.setFilterTagChip(::filterChip,::filterChip,::filterChip){
            setRecyclerList()
        }
    }

    private fun filterChip(category: Category) {
        booksList.filter {
            it.category == category
        }.also {
            libraryAdapter.updateList(it)
        }
    }

    private fun setAdapter() {
        binding.libraryRv.adapter = libraryAdapter
        libraryAdapter.setDetailClickBook(::toDetailFragment)
    }

    private fun clickBook(id: Int) {
        if (booksList[id].isFavorite) {
            favoriteList.remove(booksList[id])
            booksList[id] = booksList[id].copy(isFavorite = false)
            setRecyclerList()
        } else {
            booksList[id] = booksList[id].copy(isFavorite = true)
            favoriteList.add(booksList[id])
            setRecyclerList()
        }
    }

    private fun toDetailFragment(bookId : Int){
        val action = LibraryFragmentDirections.actionLibraryFragmentToBookDetailFragment(
            bookId,
            START_LIBRARY
        )
        findNavController().navigate(action)
    }

    private fun setRecyclerList() {
        libraryAdapter.updateList(booksList)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}