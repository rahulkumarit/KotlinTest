package com.rnd.kotlintest.activities

import android.os.Bundle
import android.widget.Toast
import com.rnd.kotlintest.R
    import kotlinx.android.synthetic.main.activity_sqlite.*

/**
 * Created by Devrepublic-14 on 12/19/2017.
 */
class SqliteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        try {
            initCoponets()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        btnSave.setOnClickListener {
            saveDataDB()
        }
    }

    private fun saveDataDB() {
        if (isValidData()) {
            Toast.makeText(this, "Valid Data now save", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidData(): Boolean {
        Toast.makeText(this, "Valid Data not now", Toast.LENGTH_SHORT).show()
        return false
    }

}