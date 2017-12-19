package com.rnd.kotlintest.activities

import android.os.Bundle
import android.widget.Toast
import com.rnd.kotlintest.R
import kotlinx.android.synthetic.main.activity_push_notifiacation.*

/**
 * Created by Devrepublic-14 on 12/11/2017.
 */
    class PushNotifiacationTestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_notifiacation)
        try {
            initCoponets()
        } catch (e: Exception) {
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        btnPush.setOnClickListener {
            Toast.makeText(this, "Push notification", Toast.LENGTH_SHORT).show()
        }
    }
}