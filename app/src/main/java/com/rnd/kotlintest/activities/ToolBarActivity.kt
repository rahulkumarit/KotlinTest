package com.rnd.kotlintest.activities

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.rnd.kotlintest.R


/**
 * Created by Devrepublic-14 on 1/2/2018.
 */
class ToolBarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_bar)
        try {
            initCoponets()
          } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        setTitle(null);
        val topToolBar = findViewById<View>(R.id.toolbar) as Toolbar
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        topToolBar.title = "Title here.."
        setSupportActionBar(topToolBar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val myActionMenuItem = menu.findItem(R.id.action_search)
        val searchView = myActionMenuItem.getActionView() as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                /* if (TextUtils.isEmpty(newText)) {
                     adapter.filter("")
                          listView.clearTextFilter()
                 } else {
                     adapter.filter(new Text)
                 }
                 */
                return true
            }
        })
        return true
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Select The Action")
        menu.add(0, v.id, 0, "Call")//groupId, itemId, order, title
        menu.add(0, v.id, 0, "SMS")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.getTitle() === "Call") {
            Toast.makeText(applicationContext, "calling code", Toast.LENGTH_LONG).show()
        } else if (item.getTitle() === "SMS") {
            Toast.makeText(applicationContext, "sending sms code", Toast.LENGTH_LONG).show()
        } else {
            return false
        }
        return true
    }

}