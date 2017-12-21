package com.rnd.kotlintest.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.rnd.kotlintest.models.Note
import com.rnd.kotlintest.utils.Util.Companion.KEY_ID


/**
 * Created by Devrepublic-14 on 12/19/2017.
 */
class NoteDbManager() {

    private val dbName = "JSANotes"
    private val dbTable = "Notes"
    private val colId = "Id"
    private val colTitle = "Title"
    private val colContent = "Content"
    private val dbVersion = 1
    private val CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + dbTable + " (" + colId + " INTEGER PRIMARY KEY," + colTitle + " TEXT, " + colContent + " TEXT);"


    inner class DatabaseHelper : SQLiteOpenHelper {
        var context: Context? = null

        constructor(context: Context) : super(context, dbName, null, dbVersion) {
            this.context = context
        }

        fun deleteNote(note: Note) {
            val db = this.writableDatabase
            db.delete(dbTable, KEY_ID + " = ?",
                    arrayOf(note.id.toString()))
            db.close()
        }

        fun getNote(note: Note): Note {
            val db = this.readableDatabase
            val cursor = db.query(dbTable, arrayOf<String>(colId, colTitle, colContent), colId + "=?",
                    arrayOf(note.id.toString()), null, null, null, null)
            if (cursor != null)
                cursor!!.moveToFirst()
            return Note(Integer.parseInt(cursor!!.getString(0)),
                    cursor!!.getString(1), cursor!!.getString(2))
        }

        // Updating single contact
        fun updateNote(note: Note): Int {

            val db = this.writableDatabase
            val values = ContentValues()
            values.put(colTitle, note.title)
            values.put(colContent, note.discription)
            // updating row
             return db.update(dbTable, values, colId + " = ?",
                     arrayOf(note.id.toString()))
          }

        fun getAllNotes(): List<Note> {
            val contactList = ArrayList<Note>()
            // Select All Query
            val selectQuery = "SELECT  * FROM " + dbTable

            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    val contact = Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
                    // Adding contact to list
                    contactList.add(contact)
                } while (cursor.moveToNext())
            }

            // return contact list
            return contactList
        }


        fun insert(title: String, des: String) {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(colTitle, title) // Contact Name
            values.put(colContent, des) // Contact Phone
            // Inserting Row
            db.insert(dbTable, null, values)
            db.close() // Closing database connection
            Toast.makeText(this.context, "inserted success", Toast.LENGTH_SHORT).show()
        }

        /*  Predefine method*/
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(CREATE_TABLE_SQL)
            Toast.makeText(this.context, " database is created", Toast.LENGTH_LONG).show()
        }

        /* Predefine method*/
        override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
            //Drop older table if existed
            db!!.execSQL("DROP TABLE IF EXISTS " + dbTable);
            //Create table again
            onCreate(db);
        }

    }

}