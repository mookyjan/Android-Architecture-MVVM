package com.mudassirkhan.realitygametask.ui.base

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mudassirkhan.realitygametask.R
import com.mudassirkhan.realitygametask.core.Router
import com.mudassirkhan.realitygametask.core.bundle
import com.mudassirkhan.realitygametask.core.event.ConnectionReceiver
import com.mudassirkhan.realitygametask.widget.CustomProgressBar

open class BaseActivity : AppCompatActivity(), ConnectionReceiver.ConnectionReceiverListener {


    private var progressBarDialog: CustomProgressBar? = null
    private val connectionReceiver = ConnectionReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        setupListeners()
    }

    private fun setupListeners() {
        applicationContext.registerReceiver(connectionReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        connectionReceiver.setListener(this)
    }

    fun startActivity(
        from: Context,
        to: Class<*>,
        parameters: HashMap<Router.Parameter, Any?> = hashMapOf(),
        clearHistory: Boolean = false,
        singleTask: Boolean = false
    ) {
        val intent = Intent(from, to)
        if (singleTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        }
        if (clearHistory) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        if (parameters.isNotEmpty()) {
            intent.putExtras(parameters.bundle)
        }

        startActivity(intent)
    }

    fun startActivity(
        from: Context,
        to: Class<*>,
        parameters: HashMap<Router.Parameter, Any?> = hashMapOf(),
        clearHistory: Boolean = false,
        singleTask: Boolean = false,
        transition: Bundle
    ) {
        val intent = Intent(from, to)
        if (singleTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        }
        if (clearHistory) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        if (parameters.isNotEmpty()) {
            intent.putExtras(parameters.bundle)
        }
        if (transition != Bundle.EMPTY) {
            startActivity(intent, transition)
        } else {
            startActivity(intent)
        }
    }


    fun showLoading(isShown: Boolean?, string: Any = R.string.progress_bar_message) {
        if (!isFinishing) {
            if (isShown!!) {
                if (progressBarDialog == null) {
                    createProgressBar(string)
                }
                progressBarDialog?.show()
            } else {
                if (progressBarDialog == null) {
                    createProgressBar(string)
                }
                progressBarDialog?.dismiss()
            }
        }
    }

    private fun createProgressBar(string: Any) {
        progressBarDialog =
            CustomProgressBar.show(this, if (string is Int) getString(string) else string as String, false)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        //TODO we can no improve it more but due to time shoratage keep it sample
        if (!isConnected) {
            Toast.makeText(this, "No Internet Connection ", Toast.LENGTH_SHORT).show()
        }
    }
}
