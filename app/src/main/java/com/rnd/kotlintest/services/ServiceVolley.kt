package com.rnd.kotlintest.services

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.rnd.kotlintest.MyApplication
import com.rnd.kotlintest.interfaces.ServiceInterface
import com.rnd.kotlintest.utils.Util
import org.json.JSONObject

/**
 * Created by Devrepublic-14 on 10/31/2017.
 */

class ServiceVolley : ServiceInterface {

    val TAG = ServiceVolley::class.java.simpleName
    val basePath = "https://api.androidhive.info/volley/person_object.json"
    val basePostPath = "http://mymedicalportfolio.in/WS/doctor_sign_up"


    override fun getStringResult(path: String, params: String, completionHandler: (response: String?) -> Unit) {
        val jsonStringReq = StringRequest(path,
                Response.Listener<String> { response ->
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    completionHandler(null)
                })
        MyApplication.instance?.addToRequestQueue(jsonStringReq)
    }


    override fun get(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {

          val jsonObjReq = object : JsonObjectRequest(Method.GET, basePostPath + path, params,
                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "/post request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }
        MyApplication.instance?.addToRequestQueue(jsonObjReq)

    }

    override fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {

        val jsonObjReq = object : JsonObjectRequest(Method.POST, basePostPath + path, params,

                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "/post request OK! Response: $response")
                    completionHandler(response)
                },

                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }
        MyApplication.instance?.addToRequestQueue(jsonObjReq)

    }

    override fun postMap(path: String, params: Map<String, String>, completionHandler: (response: JSONObject?) -> Unit) {
        val url = Util.mapToQueryString(params)

    }

}