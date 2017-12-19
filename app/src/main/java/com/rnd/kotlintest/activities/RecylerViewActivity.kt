package com.rnd.kotlintest.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.rnd.kotlintest.MyApplication
import com.rnd.kotlintest.R
import com.rnd.kotlintest.adapters.CustomAdapter
import com.rnd.kotlintest.interfaces.ItemClickListeners
import com.rnd.kotlintest.models.User
import com.rnd.kotlintest.utils.Util
import kotlinx.android.synthetic.main.activity_recyler_view.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * Created by Devrepublic-14 on 10/31/2017.
 */

class RecylerViewActivity : BaseActivity(), ItemClickListeners {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyler_view)
        try {
            initCoponets()
            wsCalling()
        } catch (e: Exception) {
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        rvTest.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val users = ArrayList<User>()
        for (i in 1..5) {
            users.add(User("Rahul Kumar:" + i, "Ahmedabad , India"))
        }
        val adapter = CustomAdapter(this, users, this)
        rvTest.adapter = adapter
    }

    override fun onClickView(pos: Int, view: View) {
        wsCalling()
    }

    private fun wsCalling() {
        //creating volley string request
        val stringRequest = object : StringRequest(Request.Method.GET, Util.WS_URL,
                Response.Listener<String> { response ->
                            try {
                        val obj = JSONObject(response)
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                       } catch (e: JSONException) {
                        e.printStackTrace()
                        }
                },
                  object : Response.ErrorListener {
                    override fun onErrorResponse(volleyError: VolleyError) {
                        Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                    }
                }) {
         }

        //adding request to queue
        MyApplication.instance?.addToRequestQueue(stringRequest)

    }


 }