package com.demo.list.epoxy

import android.view.View
import com.demo.list.R
import com.demo.list.databinding.ItemPostListBinding
import com.jatri.common.base.ViewBindingKotlinModel

data class ItemEpoxyModel(
    val postId : Int,
    val post : String,
    val onItemClick : (View.OnClickListener)
) : ViewBindingKotlinModel<ItemPostListBinding>(R.layout.item_post_list) {

    override fun ItemPostListBinding.bind() {
        idTv.text = "$postId"
        titleTv.text = post
        root.setOnClickListener(onItemClick)
    }
}