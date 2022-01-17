package com.demo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.demo.list.databinding.ItemPostListBinding
import com.jatri.common.adapter.DataBoundListAdapter
import com.jatri.entity.PostItemApiEntity

class PostListAdapter : DataBoundListAdapter<PostItemApiEntity,ItemPostListBinding>(
    object : DiffUtil.ItemCallback<PostItemApiEntity>(){
        override fun areItemsTheSame(
            oldItem: PostItemApiEntity,
            newItem: PostItemApiEntity
        ): Boolean {
            return oldItem.postId == newItem.postId
        }

        override fun areContentsTheSame(
            oldItem: PostItemApiEntity,
            newItem: PostItemApiEntity
        ): Boolean {
            return oldItem.postId == newItem.postId
        }

    }
){
    override fun createBinding(parent: ViewGroup): ItemPostListBinding  =
        ItemPostListBinding.inflate(LayoutInflater.from(parent.context),parent,false)

    override fun bind(binding: ItemPostListBinding, item: PostItemApiEntity, position: Int) {
        binding.idTv.text = "${item.postId}"
        binding.titleTv.text = item.title
    }
}