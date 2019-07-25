package com.mudassirkhan.realitygametask.ui.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mudassirkhan.realitygametask.R
import com.mudassirkhan.realitygametask.core.BaseRecyclerViewAdapter
import com.mudassirkhan.realitygametask.core.Router
import com.mudassirkhan.realitygametask.core.event.StartActivityModel
import com.mudassirkhan.realitygametask.databinding.ActivityMainBinding
import com.mudassirkhan.realitygametask.model.AllLunchPadResponse
import com.mudassirkhan.realitygametask.ui.adapter.SingleAdapter
import com.mudassirkhan.realitygametask.ui.base.BaseActivity
import com.mudassirkhan.realitygametask.widget.RecyclerViewItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var lunchPadAdapter: SingleAdapter<AllLunchPadResponse>
    private var lunchPadList = arrayListOf<AllLunchPadResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.viewModel = viewModel
        setupObservers()
        viewModel.getLunchPads()
        setupView()
        showLunchPads()

    }

    /**
     * method to observe the data and variable
     */
    private fun setupObservers() {

        viewModel.loading.observe(this, Observer {
            showLoading(it)
        })

        lunchpad_list_refresh.setOnRefreshListener {
            lunchpad_list_refresh.isRefreshing = false
            viewModel.getLunchPads()
        }

        viewModel.lunchPads.observe(this, Observer { it ->
            it ?: return@Observer
            lunchPadAdapter.run {
                lunchPadList.clear()
                lunchPadList.addAll(it)
                notifyDataSetChanged()
            }
        })

        //here we can also directly pass the LunchPad object to view the detail but for
        // scenario where little information at first stage shown and then by calling api
        // we get all the details for this purpose we only pass the id and call again the detail api
        viewModel.startActivityEvent.observe(this, Observer {
            if (it!!.transition == Bundle.EMPTY) {
                startActivity(this@MainActivity!!, Router.getClass(it.to), it.parameters, it.clearHistory)

            } else {
                startActivity(
                    this@MainActivity!!,
                    Router.getClass(it.to),
                    it.parameters,
                    it.clearHistory,
                    transition = it.transition!!
                )
            }
        })

    }


    /**
     * notify the adapter after data change
     */
    private fun showLunchPads() {
        lunchPadAdapter.notifyDataSetChanged()
    }

    /**
     * method to setup for adapter and recyclerview to view items
     */
    private fun setupView() {
        lunchPadAdapter = SingleAdapter(
            lunchPadList,
            R.layout.lunch_pad_item_row,
            object : BaseRecyclerViewAdapter.OnItemClickListener<AllLunchPadResponse> {
                override fun onItemClick(item: AllLunchPadResponse, view: View) {
                    viewModel.startActivityEvent.value = StartActivityModel(
                        Router.Destination.LUNCH_PAD_DETAIL, hashMapOf(
                            Pair(Router.Parameter.SITE_ID, item.siteId), Pair(Router.Parameter.ID, item.image)
                        )
                    )
                }
            })
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(RecyclerViewItemDecoration(this))
        binding.recyclerView.adapter = lunchPadAdapter

    }
}
