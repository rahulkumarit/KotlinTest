package com.rnd.kotlintest.activities
import android.os.Bundle
import com.rnd.kotlintest.R
import kotlinx.android.synthetic.main.activity_animation.*

/**
 * Created by Devrepublic-14 on 11/7/2017.
 */
class RxAndroidActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        try {
            initCoponets()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        txtTest.setOnClickListener {
        }

    }


}