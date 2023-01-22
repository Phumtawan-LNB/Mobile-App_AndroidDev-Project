package com.example.lab4.ui.mainsqlite

import android.content.Intent
import android.graphics.pdf.PdfDocument
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


class MainSQLite : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edEmail: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var btnUpdate: Button
    private lateinit var SQLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: StudentAdapter? = null
    private var std:StudentModel? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_mainsqlite)

        initView()
        initRecyclerVew()
        SQLiteHelper = SQLiteHelper(this)

        var buttonGoto = findViewById<Button>(R.id.bt_hi)
        buttonGoto!!.setOnClickListener {
            var intent = Intent(this, PageToView::class.java)
            startActivity(intent)
        }
        btnAdd.setOnClickListener { addStudent() }
        btnView.setOnClickListener{getStudent()}
        btnUpdate.setOnClickListener {updateStudent()}
        /*adapter?.setOnClickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            edName.setText(it.name)
            edEmail.setText(it.email)
            std = it
        }*/
        adapter?.setOnClickDeleteItem{
            deleteItemStudent(it.id)
        }
    }


    private fun getStudent(){
        val stdList = SQLiteHelper.getALLStudent()
        Log.e("pppp", "${stdList.size}")

        adapter?.addItems(stdList)
    }

    private fun addStudent(){
        val name = edName.text.toString()
        val email = edEmail.text.toString()

        if(name.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter requried field", Toast.LENGTH_SHORT).show()
        }else{
            val std = StudentModel(name = name,email = email)
            val status = SQLiteHelper.insertStudent(std)

            if(status > -1){
                Toast.makeText(this, "Student Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
                getStudent()
            }else {
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private  fun updateStudent(){
        val name = edName.text.toString()
        val email = edEmail.text.toString()

        if(name == std?.name && email == std?.email){
            Toast.makeText(this,"Record not changed....",Toast.LENGTH_SHORT).show()
            return
        }

        if(std == null)return
        val std = StudentModel(id = std!!.id, name = name, email = email )
        val status = SQLiteHelper.updateStudent(std)
        if(status > -1){
            clearEditText()
            getStudent()
        }else{
            Toast.makeText(this,"Update failed", Toast.LENGTH_SHORT).show()
        }

    }

    private  fun deleteItemStudent(id:Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete item")
        builder.setCancelable(true)

        builder.setPositiveButton("Yes"){ dialong, _ ->
            getStudent()
            dialong.dismiss()
            SQLiteHelper.deleteStudentById(id)
        }
        builder.setPositiveButton("No"){ dialong, _ ->
            dialong.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun clearEditText(){
        edName.setText("")
        edEmail.setText("")
        edName.requestFocus()
    }

    private fun initRecyclerVew(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView(){
        edName = findViewById(R.id.edName)
        edEmail = findViewById(R.id.edEmail)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        btnUpdate = findViewById(R.id.btnUpdate)
        recyclerView = findViewById(R.id.recyclerView)
    }
}