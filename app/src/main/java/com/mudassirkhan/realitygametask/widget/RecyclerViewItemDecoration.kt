package com.mudassirkhan.realitygametask.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mudassirkhan.realitygametask.R

class RecyclerViewItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var divider: Drawable? = ContextCompat.getDrawable(context, R.drawable.rv_divider)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + (divider?.intrinsicHeight ?: 0)

            divider?.setBounds(left, top, right, bottom)

            if ((parent.getChildAdapterPosition(child) == parent.adapter!!.itemCount - 1) && parent.bottom < bottom) {
                parent.setPadding(parent.paddingLeft, parent.paddingTop, parent.paddingRight, bottom - parent.bottom)
            }

            divider?.draw(c)
        }
    }
}