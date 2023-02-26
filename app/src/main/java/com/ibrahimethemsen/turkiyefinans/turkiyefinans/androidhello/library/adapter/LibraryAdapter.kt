package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.AdapterLibraryItemBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.data.Book

class LibraryAdapter : RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>(){
    private val books = mutableListOf<Book>()

    fun updateList(newBooks : List<Book>){
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

    private var clickBook : ((Int) -> Unit)? = null

    fun setClickBook(clickBook : (Int) -> Unit){
        this.clickBook = clickBook
    }

    class LibraryViewHolder(private val binding : AdapterLibraryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            book : Book,
            clickBook: ((Int) -> Unit)?
        ){
            binding.apply {
                adapterBookIv.setImageResource(book.cover)
                adapterBookTitleTv.text = book.name
                adapterBookSubTitleTv.text = this.root.context.getString(book.subtitle)
                adapterRating.rating = book.rating.toFloat()
                adapterPages.text = this.root.context.getString(R.string.pages_size,book.pages)
                if (book.isFavorite){
                    adapterFavorite.setImageResource(R.drawable.ic_bookmark_favorite)
                }else{
                    adapterFavorite.setImageResource(R.drawable.ic_bookmark)
                }
                adapterFavorite.setOnClickListener {
                    clickBook?.invoke(book.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding = AdapterLibraryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LibraryViewHolder(binding)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bind(books[position],clickBook)
    }
}