package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.addbook

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentAddBookBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.component.Category
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.userInfoMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookFragment : Fragment() {
    private var _binding: FragmentAddBookBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AddBookViewModel>()
    private var category: Category? = null
    private var pickBitmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
    }

    private fun listener() {
        binding.apply {
            addBookBtn.setOnClickListener {
                if (pickBitmap != null) {
                    checkAddBook(
                        bookName = addBookNameEt.text.toString(),
                        subTitle = addBookSubtitleEt.text.toString(),
                        pages = addBookPagesEt.text.toString().toInt(),
                        description = addBookDescriptionEt.text.toString(),
                        cover = pickBitmap,
                        category = category,
                        rating = addBookRatingEt.text.toString().toInt()
                    )
                } else {
                    Toast.makeText(requireContext(), "Bitmap null", Toast.LENGTH_SHORT).show()
                }
            }
            addBookCategoryGroup.setFilterTagChip(::setCategory,::setCategory,::setCategory){
                category = null
            }
            addBookImageBtn.setOnClickListener {
                pickPhoto()
            }
        }
    }
    private fun setCategory(category : Category){
        this.category = category
    }

    private val galleryPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            galleryIntent()
        } else {
            requireContext().userInfoMessage(R.string.add_book_no_gallery_permission)
        }
    }

    private fun galleryIntent() {
        startActivity(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
    }

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent(),
    ) { uri ->
        uri?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, it)
            }.also { bitmap ->
                pickBitmap = bitmap
                binding.addBookCoverPreview.setImageBitmap(bitmap)
            }
        }
    }

    private fun pickPhoto() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            galleryLauncher.launch("image/*")
            galleryPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            galleryIntent()
            galleryLauncher.launch("image/*")
        }
    }

    private fun checkAddBook(
        bookName: String?,
        subTitle: String?,
        pages: Int?,
        description: String?,
        cover: Bitmap?,
        language: String? = "TR",
        category: Category?,
        rating: Int? = 0
    ) {
        viewModel.apply {
            if (isCheckInputBook(
                    bookName,
                    subTitle,
                    pages,
                    description,
                    language,
                    rating
                ) && category != null
            ) {
                cover?.let {
                    addBook(
                        bookName!!,
                        subTitle!!,
                        pages!!,
                        description!!,
                        cover,
                        category,
                        language,
                        rating
                    )
                    toHomeFragment()
                }
            } else if (category == null) {
                requireContext().userInfoMessage(R.string.add_book_category)
            } else {
                requireContext().userInfoMessage(R.string.empty_text_field)
            }
        }
    }

    private fun toHomeFragment(){
        val action = AddBookFragmentDirections.actionAddBookFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}