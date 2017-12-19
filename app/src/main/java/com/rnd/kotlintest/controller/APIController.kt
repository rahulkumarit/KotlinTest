package com.rnd.kotlintest.controller

import com.rnd.kotlintest.interfaces.ServiceInterface
import org.json.JSONObject

/**
 * Created by Devrepublic-14 on 10/31/2017.
 */

class APIController constructor(serviceInjection: ServiceInterface) : ServiceInterface {

    private val service: ServiceInterface = serviceInjection

    override fun postMap(path: String, params: Map<String, String>, completionHandler: (response: JSONObject?) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getStringResult(path: String, params: String, completionHandler: (response: String?) -> Unit) {
        service.getStringResult(path, params, completionHandler)
    }

    override fun get(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        service.get(path, params, completionHandler)
    }

    override fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        service.post(path, params, completionHandler)
    }

}