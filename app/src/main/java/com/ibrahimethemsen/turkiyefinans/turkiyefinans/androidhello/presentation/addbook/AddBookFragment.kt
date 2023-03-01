package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.addbook

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentAddBookBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.library.data.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookFragment : Fragment() {
    private var _binding : FragmentAddBookBinding? = null
    private val binding  get() = _binding!!

    private val viewModel by viewModels<AddBookViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBookBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
    }
    private fun listener(){
        binding.apply {
            addBookBtn.setOnClickListener {
                viewModel.addBook(
                    "SiyasetName",
                    "deneme",
                    235,
                    "asdasd",
                    BitmapFactory.decodeResource(requireContext().resources,R.drawable.book_2),
                    Category.HISTORY,
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}