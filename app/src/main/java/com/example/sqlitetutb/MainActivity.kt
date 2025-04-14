package com.example.sqlitetutb

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dbUtil = DBUtil(this)
        dbUtil.insertEmployee("epm 22" , 100000)
        dbUtil.deleteEmployee(1)
        dbUtil.updateEmployee(2, "radha" , 20000)

        val employeeList: ArrayList<Employee> = dbUtil.getAllEmployee()

        for (i in employeeList){
            Log.d("tags" , i.toString())
        }

    }
}