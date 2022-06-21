package com.demo.list.epoxy

import android.view.View
import com.demo.list.R
import com.demo.list.databinding.ItemHorizontalPostBinding
import com.demo.list.databinding.ItemPostListBinding
import com.jatri.common.base.ViewBindingKotlinModel

class ItemEpoxyHorizontalModel(
    private val postId : Int,
    private val post : String,
    private val onItemClick : (View.OnClickListener)
) : ViewBindingKotlinModel<ItemHorizontalPostBinding>(R.layout.item_horizontal_post) {

    override fun ItemHorizontalPostBinding.bind() {
        idTv.text = "$postId"
        titleTv.text = post
        root.setOnClickListener(onItemClick)
    }
}