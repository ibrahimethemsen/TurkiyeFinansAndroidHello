package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentLibraryBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.adapter.LibraryAdapter
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.data.Category
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.data.booksList
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.data.favoriteList

class LibraryFragment : Fragment() {
    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private val libraryAdapter = LibraryAdapter()

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
    }

    private fun listener() {
        libraryAdapter.setClickBook(::clickBook)
        binding.libraryCategoryGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()){
                val selectedChip = group.findViewById<Chip>(checkedIds.first())
                when (selectedChip.text) {
                    getString(R.string.category_history) -> {
                        filterChip(Category.HISTORY)
                    }
                    getString(R.string.category_sociology) -> {
                        filterChip(Category.SOCIOLOGY)
                    }
                    getString(R.string.category_self_improvement) -> {
                        filterChip(Category.SELF_IMPROVEMENT)
                    }
                }
            }else{
                setRecyclerList()
            }
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

    private fun setRecyclerList() {
        libraryAdapter.updateList(booksList)
    }
}