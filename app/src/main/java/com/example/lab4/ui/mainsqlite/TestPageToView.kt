package com.example.lab4.ui.mainsqlite

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.R

class TestPageToView : AppCompatActivity(){
    private lateinit var bt_view: Button
    private lateinit var TestSQLiteHelper: TestSQLiteHelper
    private lateinit var recyclerViews: RecyclerView
    private var adapter: TestAdapter? = null
    //private var std:TestModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dashboard)

        initView()
        initRecyclerVew()
        TestSQLiteHelper = TestSQLiteHelper(this)
        bt_view.setOnClickListener{getStudent()}
        adapter?.setOnClickDeleteItem{
            deleteItemStudent(it.id)
        }
    }
    private fun getStudent(){
        val stdList = TestSQLiteHelper.getALLStudent()
        Log.e("pppp", "${stdList.size}")

        adapter?.addItems(stdList)
    }
    private  fun deleteItemStudent(id:Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete item")
        builder.setCancelable(true)

        builder.setPositiveButton("Yes"){ dialong, _ ->
            getStudent()
            dialong.dismiss()
            TestSQLiteHelper.deleteStudentById(id)
        }
        builder.setPositiveButton("No"){ dialong, _ ->
            dialong.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun initRecyclerVew(){
        recyclerViews.layoutManager = LinearLayoutManager(this)
        adapter = TestAdapter()
        recyclerViews.adapter = adapter
    }

    private fun initView(){
        bt_view = findViewById(R.id.bt_view)
        recyclerViews = findViewById(R.id.recyclerViews)
    }
}