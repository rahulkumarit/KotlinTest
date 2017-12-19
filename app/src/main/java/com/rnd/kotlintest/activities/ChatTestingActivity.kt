package com.rnd.kotlintest.activities

import android.os.Bundle
import com.rnd.kotlintest.R

/**
 * Created by Devrepublic-14 on 12/11/2017.
 */
class ChatTestingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_testing)
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