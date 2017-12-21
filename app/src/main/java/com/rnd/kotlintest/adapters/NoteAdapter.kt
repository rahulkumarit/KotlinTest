package com.rnd.kotlintest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.rnd.kotlintest.R
import com.rnd.kotlintest.interfaces.ItemClickListeners
import com.rnd.kotlintest.models.Note
import kotlinx.android.synthetic.main.note_item_view.view.*


/**
 * Created by Devrepublic-14 on 12/20/2017.
 */
class NoteAdapter(val context: Context, val notes: ArrayList<Note>, val itemClick: ItemClickListeners) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.note_item_view, parent, false)
        var txtTitle: TextView = view.txtTitle
        var txtDisc: TextView = view.txtDisc
        var imgEdit: ImageView = view.imgEdit
        var imgDel: ImageView = view.imgDel
        imgEdit.setOnClickListener { itemClick.onClickView(position, imgEdit) }
        imgDel.setOnClickListener { itemClick.onClickView(position, imgDel) }
        txtTitle.text = notes.get(position).title
        txtDisc.text = notes.get(position).discription
        return view
    }

    override fun getItem(position: Int): Any {
        return notes.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return notes.size
    }


}
