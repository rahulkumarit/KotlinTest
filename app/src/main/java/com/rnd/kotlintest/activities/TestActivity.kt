package com.rnd.kotlintest.activities

import android.os.Bundle
import android.widget.Toast
import com.rnd.kotlintest.R
import kotlinx.android.synthetic.main.activity_test.*

/**
 * Created by Devrepublic-14 on 11/14/2017.
 */
class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        try {
            initCoponets()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        //set listeneres
        txtTestJava.setOnClickListener {
            calljavaClass()
        }
        txtTestKotlin.setOnClickListener { callKotlineClass() }
    }

    private fun calljavaClass() {
        Toast.makeText(this, "TestActivity : ", Toast.LENGTH_SHORT).show()
    }

    private fun callKotlineClass() {

    }
}
