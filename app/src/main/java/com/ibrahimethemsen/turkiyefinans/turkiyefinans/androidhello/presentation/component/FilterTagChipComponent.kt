package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.google.android.material.chip.Chip
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.ComponentTagFilterChipBinding

enum class Category{
    HISTORY,
    SELF_IMPROVEMENT,
    SOCIOLOGY
}

class FilterTagChipComponent @JvmOverloads constructor(
    context : Context,
    attributeSet: AttributeSet? = null
) : FrameLayout(context,attributeSet){
    private val binding = ComponentTagFilterChipBinding.inflate(LayoutInflater.from(context),this,false)
    init {
        addView(binding.root)
    }
    fun setFilterTagChip(
        historyHigherOrder : (Category) -> Unit,
        sociologyHigherOrder : (Category) -> Unit,
        selfHigherOrder : (Category) -> Unit,
        elseHigherOrder : () -> Unit
        ){
        binding.filterTagChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()){
                val selectedChip = group.findViewById<Chip>(checkedIds.first())
                when (selectedChip.text) {
                    context.getString(R.string.category_history) -> {
                        historyHigherOrder.invoke(Category.HISTORY)
                    }
                    context.getString(R.string.category_sociology) -> {
                        sociologyHigherOrder.invoke(Category.SOCIOLOGY)
                    }
                    context.getString(R.string.category_self_improvement) -> {
                        selfHigherOrder.invoke(Category.SELF_IMPROVEMENT)
                    }
                }
            }else{
                elseHigherOrder.invoke()
            }
        }
    }
}