package com.rnd.kotlintest.activities

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.view.MenuItem
import android.view.View
import com.rnd.kotlintest.BottomNavigationBehavior
import com.rnd.kotlintest.R
import com.rnd.kotlintest.fragments.CartFragment
import com.rnd.kotlintest.fragments.GiftsFragment
import com.rnd.kotlintest.fragments.ProfileFragment
import com.rnd.kotlintest.fragments.StoreFragment


/**
 * Created by Devrepublic-14 on 12/26/2017.
 */
class NavigationActivity : BaseActivity() {

    var toolbar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            initCoponets()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        toolbar = getSupportActionBar();
        val navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // attaching bottom sheet behaviour - hide / show on scroll
        val layoutParams = navigation.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior()
        toolbar?.setTitle("Shop")
        loadFragment(StoreFragment())
    }

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
            val fragment: Fragment
            when (item.getItemId()) {
                R.id.navigation_shop -> {
                    toolbar?.setTitle("Shop")
                    loadFragment(StoreFragment())
                    return true
                }
                R.id.navigation_gifts -> {
                    toolbar?.setTitle("My Gifts")
                    loadFragment(GiftsFragment())
                    return true
                }
                R.id.navigation_cart -> {
                    toolbar?.setTitle("Cart")
                    loadFragment(CartFragment())
                    return true
                }
                R.id.navigation_profile -> {
                    toolbar?.setTitle("Profile")
                    loadFragment(ProfileFragment())
                    return true
                }
            }
            return false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}