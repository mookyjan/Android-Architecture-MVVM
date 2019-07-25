package com.mudassirkhan.realitygametask.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mudassirkhan.realitygametask.R
import com.mudassirkhan.realitygametask.databinding.LunchPadItemRowBinding
import com.mudassirkhan.realitygametask.model.AllLunchPadResponse
import kotlinx.android.synthetic.main.lunch_pad_item_row.view.*

class LunchPadAdapter(
    var activity: Activity,
    private var lunchPadList: List<AllLunchPadResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var modifiesList = listOf<AllLunchPadResponse>()

    init {
        updateList(lunchPadList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LunchPadItem(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.lunch_pad_item_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return modifiesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = modifiesList.get(position)
        val name = dataItem.siteNameLong
        holder.itemView.txt_name.text = name.substring(0, 15)

    }

    fun updateList(lunchPadList: List<AllLunchPadResponse>) {
        modifiesList = lunchPadList
    }

    inner class LunchPadItem(val binding: LunchPadItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LunchPadItem) {
//            binding.model=item
        }
    }
}