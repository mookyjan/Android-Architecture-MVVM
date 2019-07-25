package com.mudassirkhan.realitygametask.ui.adapter

import com.mudassirkhan.realitygametask.core.SingleRecyclerViewLayoutAdapter

class SingleAdapter<T>(val item: List<T>, layoutId: Int, itemClickListener: OnItemClickListener<T>?) :
    SingleRecyclerViewLayoutAdapter<T>(layoutId, itemClickListener) {
    override fun getItemCount(): Int {
        return item.size
    }

    override fun getItemForPosition(position: Int): T {
        return item[position]
    }
}