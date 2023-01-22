package com.example.lab4.ui.mainsqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.R

class main_menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_dashboard)

        var btngoto91 = findViewById<Button>(R.id.bt_main_91)
        btngoto91!!.setOnClickListener {
            var intent = Intent(this, Testsqlite91saved::class.java)
            startActivity(intent)
        }

        var btngotoHis = findViewById<Button>(R.id.bt_main_his)
        btngotoHis!!.setOnClickListener {
            var intent1 = Intent(this, TestPageToView::class.java)
            startActivity(intent1)
        }

        var btngoto90 = findViewById<Button>(R.id.bt_main_90)
        btngoto90!!.setOnClickListener {
            var intent2 = Intent(this, Sqlite90Saved::class.java)
            startActivity(intent2)
        }


    }
}