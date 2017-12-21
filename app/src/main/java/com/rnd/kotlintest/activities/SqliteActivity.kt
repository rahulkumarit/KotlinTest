package com.rnd.kotlintest.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rnd.kotlintest.R
import com.rnd.kotlintest.adapters.NoteAdapter
import com.rnd.kotlintest.db.NoteDbManager
import com.rnd.kotlintest.interfaces.ItemClickListeners
import com.rnd.kotlintest.models.Note
import kotlinx.android.synthetic.main.activity_sqlite.*
import kotlinx.android.synthetic.main.note_item_view.*


/**
 * Created by Devrepublic-14 on 12/19/2017.
 */
class SqliteActivity : BaseActivity(), ItemClickListeners {

    var title: String = "";
    var description: String = "";
    var id = 0
    var isEditable: Boolean = false
    var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        try {
            initCoponets()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        btnSave.setOnClickListener {
            if (isEditable) {
                isEditable = false
                editRecords()
            } else {
                saveDataDB()
            }
        }
    }

    private fun editRecords() {
        var database = NoteDbManager().DatabaseHelper(this)
        val number = database.updateNote(database.getAllNotes().get(position))
        Toast.makeText(this, "id :" + number, Toast.LENGTH_SHORT).show()
        addUpdateList(database);
        edtTitle.text.clear()
        edtDis.text.clear()
    }

    private fun saveDataDB() {
        if (isValidData()) {
            var database = NoteDbManager().DatabaseHelper(this)
            database.insert(title, description)
            addUpdateList(database)
            edtTitle.text.clear()
            edtDis.text.clear()
        } else {
        }
    }

    private fun addUpdateList(database: NoteDbManager.DatabaseHelper) {
        var notes = database.getAllNotes()
        var noteAdapter = NoteAdapter(this, notes as ArrayList<Note>, this)
        lvNote.adapter = noteAdapter
    }

    override fun onClickView(pos: Int, view: View) {
        if (view.id == imgDel.id) {
            var database = NoteDbManager().DatabaseHelper(this)
            database.deleteNote(database.getAllNotes().get(pos))
            addUpdateList(database)
        } else if (view.id == imgEdit.id) {
            isEditable = true
            var database = NoteDbManager().DatabaseHelper(this)
            edtTitle.setText(database.getNote(database.getAllNotes().get(pos)).title)
            edtDis.setText(database.getNote(database.getAllNotes().get(pos)).discription)
            position = pos
        }
    }

    private fun isValidData(): Boolean {
        title = edtTitle.text.toString()
        description = edtDis.text.toString()
        if (title.isEmpty()) {
            Toast.makeText(this, "Enter title..", Toast.LENGTH_SHORT).show()
            return false
        } else if (description.isEmpty()) {
            Toast.makeText(this, "Enter discription.", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }
}





