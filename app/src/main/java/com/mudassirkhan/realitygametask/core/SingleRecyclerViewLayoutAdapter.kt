package com.mudassirkhan.realitygametask.core

abstract class SingleRecyclerViewLayoutAdapter<T>(
    private val layoutId: Int,
    itemClickListener: OnItemClickListener<T>?
) : BaseRecyclerViewAdapter<T>(itemClickListener) {
    override fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }
}