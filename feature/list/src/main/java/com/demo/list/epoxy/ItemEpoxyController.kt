package com.demo.list.epoxy

import android.view.View
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.jatri.entity.PostItemApiEntity

class ItemEpoxyController(
    private val onItemSelected:(item:String)->Unit
) : EpoxyController(){

    var itemList = listOf<PostItemApiEntity>()

    init {
        setFilterDuplicates(true)
    }

    override fun buildModels() {
        val itemViewList = mutableListOf<EpoxyModel<View>>()
        itemList.forEach { item->
            itemViewList.add(
                ItemEpoxyHorizontalModel(
                    item.postId,
                    item.title
                ){
                    onItemSelected.invoke(item.title)
                }.id("hor_post_list","${item.postId}")
            )
        }

        DividerEpoxyModel(
            "Horizontal Post View"
        ).id("title_id","horizontal_post_view").addTo(this)

        CarouselModel_()
            .id("item_carousel")
            .models(itemViewList)
            .addTo(this)

        DividerEpoxyModel(
            "Vertical Post View"
        ).id("title_id","vertical_post_view").addTo(this)

        itemList.forEach { item->
            ItemEpoxyModel(
                item.postId,
                item.title
            ){
                onItemSelected.invoke(item.title)
            }.id("post_list", "${item.postId}").addTo(this)
        }
    }
}