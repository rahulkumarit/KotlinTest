package com.rnd.kotlintest.activities

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.rnd.kotlintest.R
import kotlinx.android.synthetic.main.activity_dexter_permission.*

/**
 * Created by Devrepublic-14 on 1/1/2018.
 */
class DexterPermissionsLibraryActivity : BaseActivity(), PermissionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dexter_permission)
        try {
            initCoponets()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        txtSinglePermission.setOnClickListener {
            Toast.makeText(this, "Click Single", Toast.LENGTH_SHORT).show()
            Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(this).check()
            }
        txtMultiPermission.setOnClickListener { }
        Toast.makeText(this, "Click Single", Toast.LENGTH_SHORT).show()
    }


    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Toast.makeText(this, "Dexter granted permission..", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
        token?.continuePermissionRequest();
        Toast.makeText(this, "Not implemented..", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this, "Dexter denied permission..", Toast.LENGTH_SHORT).show()
    }


}