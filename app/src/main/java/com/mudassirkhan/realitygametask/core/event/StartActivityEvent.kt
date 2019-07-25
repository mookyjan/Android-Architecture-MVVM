package com.mudassirkhan.realitygametask.core.event

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mudassirkhan.realitygametask.core.Router


/**
 * Created by Mudassir on 12/05/2019.
 */

open class StartActivityEvent : SingleLiveEvent<StartActivityModel>() {
    fun observe(owner: LifecycleOwner, observer: StartActivityObserver) {
        super.observe(owner, Observer { t ->
            if (t == null) {
                return@Observer
            }
//            if (t.hasResults || t.requestCode != 0) {
//                observer.onStartActivityForResult(t)
//            } else {
            observer.onStartActivity(t)
//            }
        })
    }

    interface StartActivityObserver {
        fun onStartActivity(data: StartActivityModel)
//        fun onStartActivityForResult(data: StartActivityModel)
    }
}

data class StartActivityModel(
    val to: Router.Destination,
    val parameters: HashMap<Router.Parameter, Any?> = hashMapOf(),
    val requestCode: Int = 0,
    val hasResults: Boolean = false,
    val clearHistory: Boolean = false,
    val singleTask: Boolean = false,
    val transition: Bundle? = Bundle.EMPTY
)
