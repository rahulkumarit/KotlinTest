package com.rnd.kotlintest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.rnd.kotlintest.R
import com.rnd.kotlintest.models.PizzaModel
import kotlinx.android.synthetic.main.list_view_layout.view.*

/**
 * Created by Devrepublic-14 on 12/15/2017.
 */
class ListViewAdapter(val context: Context, val pizzamodels: ArrayList<PizzaModel>) : BaseAdapter() {

    override fun getView(i: Int, view: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_view_layout, parent, false)
        var txtId: TextView = view.txtId
        txtId.text = pizzamodels.get(i).id + "," + pizzamodels.get(i).name + "," + pizzamodels.get(i).cost + "," + pizzamodels.get(i).description
        return view
    }

    override fun getItem(i: Int): Any {
        return pizzamodels.get(i)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return pizzamodels.size
    }


}



