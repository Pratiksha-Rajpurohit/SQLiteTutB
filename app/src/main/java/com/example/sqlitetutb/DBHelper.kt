package com.example.sqlitetutb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log



class DBHelper(
    context: Context?,
    name: String?,
    cursor : SQLiteDatabase.CursorFactory?,
    version: Int

    ) : SQLiteOpenHelper(context, name, cursor, version) {
    override fun onCreate(db: SQLiteDatabase?) {

        Log.d("created" , "created")
        db?.execSQL("CREATE TABLE IF NOT EXISTS Employee(id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT ,salary INTEGER ) ")


    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
//      Toast.makeText(context , "updated" , Toast.LENGTH_LONG).show()
        Log.d("updated" , "updated")

    }


    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }


}