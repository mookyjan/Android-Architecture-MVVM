package com.mudassirkhan.realitygametask.core

import com.mudassirkhan.realitygametask.ui.detail.LunchPadDetailActivity
import com.mudassirkhan.realitygametask.ui.main.MainActivity
import com.mudassirkhan.realitygametask.ui.rocket.RocketListActivity


/**
 * Created by Mudassir on 27/6/2019.
 */

open class Router {


    enum class Destination {
        MAIN,
        LUNCH_PAD_DETAIL,
        ROCKET_LIST
    }

    enum class Parameter {
        SITE_ID,
        ID,
        LOCATION


    }

    companion object {
        fun getClass(destination: Destination): Class<*> {
            return when (destination) {
                Destination.MAIN -> MainActivity::class.java
                Destination.LUNCH_PAD_DETAIL -> LunchPadDetailActivity::class.java
                Destination.ROCKET_LIST -> RocketListActivity::class.java


            }
        }
    }
}