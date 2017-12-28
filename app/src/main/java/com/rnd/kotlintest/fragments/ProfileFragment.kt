package com.rnd.kotlintest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rnd.kotlintest.R

/**
 * Created by Devrepublic-14 on 12/26/2017.
 */
class ProfileFragment : BaseFragment() {

    fun newInstance(param1: String, param2: String): ProfileFragment {
        val fragment = ProfileFragment()
        val args = Bundle()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_profile, container, false);
    }


}