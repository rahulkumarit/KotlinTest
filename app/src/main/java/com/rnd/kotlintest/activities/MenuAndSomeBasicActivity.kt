package com.rnd.kotlintest.activities

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.rnd.kotlintest.R
import kotlinx.android.synthetic.main.activity_menu.*

/**
 * Created by Devrepublic-14 on 1/3/2018.
 */
class MenuAndSomeBasicActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        registerForContextMenu(btnConClick)
     }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Select The Action")
        menu?.add(0, v!!.id, 0, "Call")//groupId, itemId, order, title
        menu?.add(0, v!!.id, 0, "SMS")
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return super.   onContextItemSelected(item)
        if (item?.getTitle() === "Call") {
            Toast.makeText(applicationContext, "calling code", Toast.LENGTH_LONG).show()
        } else if (item?.getTitle() === "SMS") {
            Toast.makeText(applicationContext, "sending sms code", Toast.LENGTH_LONG).show()
        } else {
            return false
        }
        return true
    }

}