package com.rnd.kotlintest.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import com.rnd.kotlintest.R
import kotlinx.android.synthetic.main.activity_basic.*
import java.util.*

/**
 * Created by Devrepublic-14 on 11/2/2017.
 */

class BasicFormTestActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
        try {
            initCoponets()
          } catch (e: Exception) {
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        btnSubmit.setOnClickListener {
            wsCalling()
        }
        txtDOB.setOnClickListener {
            workingWithDatePicker()
        }
    }

    private fun workingWithDatePicker() {
        val c = Calendar.getInstance()

       val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            txtDOB.setText("" + dayOfMonth + " " + monthOfYear + ", " + year)
        }, year, month, day)
        dpd.show()
    }

    private fun wsCalling() {
        val name = edtName.text.toString().trim()
        val address = edtAddress.text.toString().trim()
        val radioId = radioGroup.check(radioGroup.checkedRadioButtonId).toString()
        Toast.makeText(this, "Gender: " + radioId, Toast.LENGTH_SHORT).show()
    }

}