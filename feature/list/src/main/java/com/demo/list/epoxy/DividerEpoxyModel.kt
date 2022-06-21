package com.demo.list.epoxy

import android.view.View
import com.demo.list.R
import com.demo.list.databinding.ItemPostListBinding
import com.demo.list.databinding.ItemTitleBinding
import com.jatri.common.base.ViewBindingKotlinModel

data class DividerEpoxyModel (
    private val title : String,
) : ViewBindingKotlinModel<ItemTitleBinding>(R.layout.item_title) {

    override fun ItemTitleBinding.bind() {
        titleTv.text = title
    }
}