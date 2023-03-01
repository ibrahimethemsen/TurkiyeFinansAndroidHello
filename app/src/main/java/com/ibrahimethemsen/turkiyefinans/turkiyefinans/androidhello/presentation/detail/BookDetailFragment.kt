package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentBookDetailBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.library.data.booksList
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.library.data.favoriteList
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.Constants.START_FAVORITE
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.Constants.START_LIBRARY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : Fragment() {
    private var _binding : FragmentBookDetailBinding? = null
    private val binding get() = _binding!!
    private val args  : BookDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookDetailBind(args.id)
        listener()
    }
    private fun listener(){
        binding.bookDetailBackBtn.setOnClickListener {
            setClickBackBtn()
        }
        binding.bookDetailFavoriteBtn.setOnClickListener {
            clickFavorite(args.id)
        }
    }
    private fun clickFavorite(id: Int) {
        if (booksList[id].isFavorite) {
            favoriteList.remove(booksList[id])
            booksList[id] = booksList[id].copy(isFavorite = false)
            setFavoriteIcon(booksList[id].isFavorite)
        } else {
            booksList[id] = booksList[id].copy(isFavorite = true)
            favoriteList.add(booksList[id])
            setFavoriteIcon(booksList[id].isFavorite)
        }
    }

    private fun setClickBackBtn(){
        when(args.start){
            START_LIBRARY -> {
                val action =
                    BookDetailFragmentDirections.actionBookDetailFragmentToLibraryFragment()
                findNavController().navigate(action)
            }
            START_FAVORITE -> {
                val action =
                    BookDetailFragmentDirections.actionBookDetailFragmentToFavoriteFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun bookDetailBind(id : Int){
        val book = booksList[id]
        binding.apply {
            bookDetailNameTv.text = book.name
            bookDetailSubTitleTv.text = getString(book.subtitle)
            bookDetailRatingTv.text = getString(R.string.book_detail_rating,book.rating)
            bookDetailLanguageTv.text = getString(R.string.book_detail_language,book.language)
            bookDetailPageTv.text = getString(R.string.book_detail_page,book.pages)
            bookDetailDescriptionTextTv.text = getString(book.description)
            bookDetailCover.setImageResource(book.cover)
            setFavoriteIcon(book.isFavorite)
        }
    }

    private fun setFavoriteIcon(isFavorite : Boolean){
        if (isFavorite){
            binding.bookDetailFavoriteBtn.setImageResource(R.drawable.ic_bookmark_favorite)
        }else{
            binding.bookDetailFavoriteBtn.setImageResource(R.drawable.ic_bookmark)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}