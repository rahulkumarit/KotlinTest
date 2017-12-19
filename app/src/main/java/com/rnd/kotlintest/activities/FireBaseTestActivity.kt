package com.rnd.kotlintest.activities

import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.rnd.kotlintest.R
import kotlinx.android.synthetic.main.activity_firebase.*


/**
 * Created by Devrepublic-14 on 11/1/2017.
 */
class FireBaseTestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)
        try {
            initCoponets()
        } catch (e: Exception) {
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        txtFireBaseAdd.setOnClickListener {
            writeInFB()
        }
        txtFireBaseRetrive.setOnClickListener {
            Toast.makeText(this, "Retrive", Toast.LENGTH_SHORT).show()
        }
    }

    fun writeInFB() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        myRef.setValue("Hello, World!")
    }

}
