package com.rnd.kotlintest.activities

import android.os.Bundle

/**
 * Created by Devrepublic-14 on 12/28/2017.
 */
class TestFundamental : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            initCoponets()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun initCoponets() {
        super.initCoponets()
    }


}