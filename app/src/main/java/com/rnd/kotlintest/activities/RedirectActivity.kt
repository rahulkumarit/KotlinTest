package com.rnd.kotlintest.activities

import android.os.Bundle
import com.rnd.kotlintest.R

/**
 * Created by Devrepublic-14 on 12/12/2017.
 */
class RedirectActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_redirect)
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