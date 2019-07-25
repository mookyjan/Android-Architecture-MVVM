package com.mudassirkhan.realitygametask.ui.rocket

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mudassirkhan.realitygametask.R
import com.mudassirkhan.realitygametask.core.Router
import com.mudassirkhan.realitygametask.databinding.ActivityRocketListBinding
import com.mudassirkhan.realitygametask.model.rocket.RocketResponse
import com.mudassirkhan.realitygametask.ui.adapter.SingleAdapter
import com.mudassirkhan.realitygametask.ui.base.BaseActivity
import com.mudassirkhan.realitygametask.widget.RecyclerViewItemDecoration
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class RocketListActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: RocketListViewModel
    lateinit var mBinding: ActivityRocketListBinding
    private lateinit var rocketListAdapter: SingleAdapter<RocketResponse>
    private var rocketList = arrayListOf<RocketResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        mBinding = DataBindingUtil.setContentView(this@RocketListActivity, R.layout.activity_rocket_list)
        mBinding.viewModel = viewModel
        setupObservers()
        setupView()
        getRocketList()
    }

    private fun setupObservers() {
        viewModel.loading.observe(this, Observer {
            showLoading(it)
        })
    }

    /**
     * method to get list of rocket
     */
    private fun getRocketList() {
        var rocketList1: List<String> = ArrayList<String>()
        if (intent.hasExtra(Router.Parameter.LOCATION.name)) {
            rocketList1 = intent.getStringArrayListExtra(Router.Parameter.LOCATION.name)
            Timber.d("Rocket List $rocketList1")
        }
        viewModel.getRocketList(rocketList1)
        viewModel.rocketList.observe(this, Observer {
            it ?: return@Observer
            rocketListAdapter.run {
                rocketList.clear()
                rocketList.addAll(it)
                notifyDataSetChanged()
            }

        })
    }

    //setup the adapter for recyclerview to view items list
    private fun setupView() {
        //TODO we can also do here like the lunchPad item so that onClick go to detail page but this feature is not done yet
        rocketListAdapter = SingleAdapter(rocketList, R.layout.rocket_item_row, null)
        mBinding.rVRocketList.layoutManager = LinearLayoutManager(this)
        mBinding.rVRocketList.addItemDecoration(RecyclerViewItemDecoration(this))
        mBinding.rVRocketList.adapter = rocketListAdapter

    }
}
