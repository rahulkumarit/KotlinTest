package com.rnd.kotlintest.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rnd.kotlintest.R
import com.rnd.kotlintest.interfaces.ItemClickListeners
import com.rnd.kotlintest.models.User
import kotlinx.android.synthetic.main.list_layout.view.*

/**
 * Created by Devrepublic-14 on 10/31/2017.
 */

class CustomAdapter(val context: Context, val userList: ArrayList<User>, val itemClick: ItemClickListeners) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val user: User = userList[position]
        holder?.txtViewName?.text = user.name
        holder?.txtViewAddress?.text = user.address
        holder?.txtViewAddress?.setOnClickListener {
            itemClick.onClickView(position,holder?.txtViewName)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtViewName = view.txtViewName
        val txtViewAddress = view.txtViewAddress
    }

}