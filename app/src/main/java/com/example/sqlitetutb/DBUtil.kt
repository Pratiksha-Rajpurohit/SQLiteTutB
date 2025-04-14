package com.example.sqlitetutb

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DBUtil(context: Context) {

    var dbEmployee: SQLiteDatabase = DBHelper(context, "BitC_Database", null, 1).writableDatabase


    fun insertEmployee(name: String, salary: Int) {
//            empty object
        var contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("salary", salary)

        var rowId = dbEmployee.insert("Employee", null, contentValues)
        Log.d("tags", rowId.toString())

//            for (i in 2..10){
//                var values = ContentValues()
//                values.put("name", "emp $i")
//                values.put("salary", i*10000)
//                var rowId = dbEmployee.insert("Employee", null, values)
//                Log.d("rowId" , rowId.toString())
//
//
//            }


    }

    fun deleteEmployee(id: Int) {
        dbEmployee.delete("Employee", "id = ?", arrayOf(id.toString()))
        Log.d("tags", "deleted")
    }

    fun updateEmployee(id: Int, name: String, salary: Int) {
        var contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("salary", salary)
        dbEmployee.update("Employee", contentValues, "id = ?", arrayOf(id.toString()))

        Log.d("tags", "updated")

    }

    fun getAllEmployee(): ArrayList<Employee> {
        var employeeList = ArrayList<Employee>()
        var cursor = dbEmployee.query("Employee", null, null, null, null, null, null)

        while (cursor.moveToNext()){

            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var salary = cursor.getInt(2)

            var employee = Employee(id,name , salary)
            employeeList.add(employee)

        }

        return employeeList


    }


}
