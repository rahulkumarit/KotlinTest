package com.rnd.kotlintest.activities

import android.os.Bundle
import android.widget.Toast
import com.rnd.kotlintest.R
import com.rnd.kotlintest.controller.APIController
import com.rnd.kotlintest.services.ServiceVolley
import com.rnd.kotlintest.utils.Util
import kotlinx.android.synthetic.main.activity_ws_calling.*
import org.json.JSONObject

/**
 * Created by Devrepublic-14 on 11/16/2017.
 */
class WsCallingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws_calling)
        try {
            initCoponets()
        } catch (e: Exception) {
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        txtWsGet.setOnClickListener {
            val service = ServiceVolley()
            val apiController = APIController(service)
            val params = JSONObject()
            val meMap = HashMap<String, String>()
            meMap.put("first_name", "Rahul")
            meMap.put("last_name", "Yadav")
            meMap.put("middle_name", "kumar ")
            meMap.put("user_type", "Doctor")
            meMap.put("mobile_no", "9584288 185")
            meMap.put("device_type", "Android")
            meMap.put("latitude", "23.010086")
            meMap.put("longitude", "72.5058877")
            meMap.put("user_type", "Doctor")
            meMap.put("phone_no", "9584288185")
            meMap.put("specialization", "This is use for testing purpose")
            meMap.put("degree", "15")
            meMap.put("gender", "Male")
            meMap.put("hospital_address", "Patna,Bihar")
            meMap.put("email", "rahul_test_121233@gmail.com")
            meMap.put("country_id", "91")
            meMap.put("password", "123456")
            meMap.put("image", "xyz")

            /*device_type=Android
         &latitude=23.010086
&degree=15
&phone_no=9584288185
&gender=Male
&country_id=99
&device_token=
&first_name=Tst
&email=rahulkumar%40test.com
&longitude=72.5058877
&user_type=Doctor
&middle_name=Hhnjj
&mobile_no=9685054765
&specialization=Tesbjkkkh
&password=123456
&hospital_address=Hzhxjcfkgkg
&last_name=Gbjjkk*/
            val path = Util.mapToQueryString(meMap)
            /* params.put("email", "foo@email.com")
              params.put("password", "barpass")*/
            apiController.post(path, params) { response ->
                // Parse the result
                Toast.makeText(this, "Ws Response" + response, Toast.LENGTH_SHORT).show()
            }
        }
    }
}