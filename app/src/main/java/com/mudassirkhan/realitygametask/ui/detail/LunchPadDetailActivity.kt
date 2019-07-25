package com.mudassirkhan.realitygametask.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.realitygametask.R
import com.mudassirkhan.realitygametask.core.Router
import com.mudassirkhan.realitygametask.core.event.StartActivityEvent
import com.mudassirkhan.realitygametask.core.event.StartActivityModel
import com.mudassirkhan.realitygametask.databinding.ActivityLunchPadDetailBinding
import com.mudassirkhan.realitygametask.model.SingleLunchPadResponse
import com.mudassirkhan.realitygametask.ui.base.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_lunch_pad_detail.*
import java.util.*
import javax.inject.Inject

class LunchPadDetailActivity : BaseActivity() {

    //inject  the viewModel
    @Inject
    lateinit var mLunchPadDetailViewModel: LunchPadDetailViewModel
    lateinit var mBinding: ActivityLunchPadDetailBinding
    lateinit var mLunchPad: SingleLunchPadResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        mBinding = DataBindingUtil.setContentView(this@LunchPadDetailActivity, R.layout.activity_lunch_pad_detail)
        mBinding.viewModel = mLunchPadDetailViewModel
        setupObservers()
        getLunchPadDetail()
    }

    private fun setupObservers() {
        mLunchPadDetailViewModel.loading.observe(this, Observer {
            showLoading(it)
        })
        activity_view_lunchpad_parent_layout.setOnRefreshListener {
            activity_view_lunchpad_parent_layout.isRefreshing = false
            getLunchPadDetail()
        }

        //TODO we can do more work on error handling to improve it but for simplicity keep it here sample
        mLunchPadDetailViewModel.error.observe(this, Observer {

            Timber.d { "exception ${it.message}" }


        })

        //open the map to view location
        mLunchPadDetailViewModel.viewMapEvent.observe(this, Observer {
            val location = mLunchPadDetailViewModel.eventLocation.get().toString().split(",")
            val uri = String.format(Locale.ENGLISH, "geo:0,0?q=") + Uri.encode(
                String.format(
                    "%s@%f,%f",
                    "",
                    location[0].toDouble(),
                    location[1].toDouble(),
                    "UTF-8"
                )
            )
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        })


        mLunchPadDetailViewModel.startActivityEvent.observe(
            this@LunchPadDetailActivity,
            object : StartActivityEvent.StartActivityObserver {
                override fun onStartActivity(data: StartActivityModel) {
                    startActivity(
                        this@LunchPadDetailActivity,
                        Router.getClass(data.to),
                        data.parameters,
                        data.clearHistory
                    )
                }

            })

    }

    private fun getLunchPadDetail() {
        if (intent.hasExtra(Router.Parameter.SITE_ID.name)) {
            mLunchPadDetailViewModel.getLunchPadDetail(
                intent.getStringExtra(Router.Parameter.SITE_ID.name),
                intent.getStringExtra(Router.Parameter.ID.name)
            )
        }
    }


}
