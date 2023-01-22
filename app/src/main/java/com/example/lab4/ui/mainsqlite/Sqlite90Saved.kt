package com.example.lab4.ui.mainsqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.R

class Sqlite90Saved : AppCompatActivity() {
    private lateinit var editMoney: EditText
    private lateinit var editBonus: EditText
    private lateinit var editextra: EditText
    private lateinit var editchild: EditText
    private lateinit var editparent: EditText
    private lateinit var editparent2: EditText
    private lateinit var editextra2: EditText
    private lateinit var editspouse: EditText

    private lateinit var calbutton: Button
    private lateinit var TestSQLiteHelper: TestSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sql90saved)

        initView()
        TestSQLiteHelper = TestSQLiteHelper(this)

        var buttonGoto = findViewById<Button>(R.id.bt_views)
        buttonGoto!!.setOnClickListener {
            var intent = Intent(this, TestPageToView::class.java)
            startActivity(intent)
        }

        val resultRange = findViewById<TextView>(R.id.result)
        val resultRange2 = findViewById<TextView>(R.id.result2)

        var ans : Double = 0.0
        var money : Double = 0.0
        var bonus : Double = 0.0
        var parent : Double = 0.0
        var parent2 : Double = 0.0
        var child : Double = 0.0
        var extra: Double = 0.0
        var extra2: Double = 0.0
        var ans1 : Double = 0.0
        var spouse : Double = 0.0

        calbutton.setOnClickListener {
            val spouseCheck = editspouse.text.toString()
            if(spouseCheck.isEmpty()){
                Toast.makeText(this, "กรุณาใส่ข้อมูล!", Toast.LENGTH_SHORT).show()
            }else{
                money = editMoney.text.toString().toDouble()*12
                bonus = editBonus.text.toString().toDouble()
                extra = editextra.text.toString().toDouble()
                extra2 = editextra2.text.toString().toDouble()
                child  = editchild.text.toString().toDouble()
                parent = editparent.text.toString().toDouble()
                parent2 = editparent.text.toString().toDouble()
                spouse = editspouse.text.toString().toDouble()
                resultRange.text = "Result : " +ans1

                //คำนวนเงินรายรับ
                if(money >= 100000){
                    money = (money-100000)
                }else{
                    money = (money*50)/100
                }
                ans1 = ((money+bonus+extra)-60000)

                //ค่าลดย่อน
                if(parent > 0.0){
                    parent = parent*30000
                }
                if(parent2 > 0.0){
                    parent2 = parent2*30000
                    ans1 = ans1-parent2
                }
                if(child > 0.0){
                    child = child*30000
                    ans1 = ans1-child
                }
                if(extra2 > 0.0){
                    ans1 = ans1-extra2
                }
                if(spouse > 0.0){
                    ans1 = ans1-60000
                }

                //คำนวนภาษี
                if(ans1 >= 5000001){
                    ans1 = (((ans1-5000000)*0.35)+600000)
                    resultRange.text = "อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 35 %+"
                    resultRange2.setText("Result :"+ans1)
                }else if (2000001 <= ans1 && ans1 <= 5000000){
                    ans1 = (((ans1-2000000)*0.30)+250000)
                    resultRange.text = "อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 30 %"
                    resultRange2.setText("Result :"+ans1)
                }else if (1000001 <= ans1 && ans1 <= 2000000){
                    ans1 = (((ans1-1000000)*0.25)+50000)
                    resultRange.text = "อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 25 %"
                    resultRange2.setText("Result :"+ans1)
                }else if (750001 <= ans1 && ans1 <= 1000000) {
                    ans1 = (((ans1 - 750000) * 0.20) + 37500)
                    resultRange.text = "อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 20 %"
                    resultRange2.setText("Result :"+ans1)
                }else if (500001 <= ans1 && ans1 <= 750000) {
                    ans1 = (((ans1 - 500000) * 0.15) + 20000)
                    resultRange.text = "อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 15 %"
                    resultRange2.setText("Result :"+ans1)
                }else if (300001 <= ans1 && ans1 <= 500000) {
                    ans1 = (((ans1 - 300000) * 0.10) + 7500)
                    resultRange.text = "อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 10 %"
                    resultRange2.setText("Result :"+ans1)
                }else if (150001 <= ans1 && ans1 <= 300000) {
                    ans1 = ((ans1 - 150000) * 0.05)
                    resultRange.text = "อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 5 %"
                    resultRange2.setText("Result :"+ans1)
                }else{
                    resultRange.text = "บุคคลมีรายได้ 0 - 150000 ได้รับการยกเว้น"
                    resultRange2.setText("Result :" + "ไม่ต้องจ่ายจ้าาาา")
                }


                val money = editMoney.text.toString()
                if(money.isEmpty() /*|| son.isEmpty() || parent.isEmpty() || parents.isEmpty() || etc.isEmpty() || somrot.isEmpty()*/){
                    Toast.makeText(this, "Please enter requried field", Toast.LENGTH_SHORT).show()
                }else{
                    val std = TestModel(money = money, ans = ans1.toString())
                    val status = TestSQLiteHelper.insertStudent(std)

                    if(status > -1){
                        Toast.makeText(this, "Student Added...", Toast.LENGTH_SHORT).show()
                        clearEditText()
                    }else {
                        Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun clearEditText(){
        editMoney.setText("")
        editBonus.setText("")
        editchild.setText("")
        editextra.setText("")
        editextra2.setText("")
        editparent.setText("")
        editparent2.setText("")
        editextra2.setText("")
        editspouse.setText("")
        editMoney.requestFocus()
    }

    private fun initView(){
        editMoney = findViewById(R.id.editmoney)
        editchild = findViewById(R.id.editchild)
        editspouse = findViewById(R.id.editspouse)
        editextra = findViewById(R.id.editextra)
        editextra2 = findViewById(R.id.editextra2)
        editBonus = findViewById(R.id.editbonus)
        editparent = findViewById(R.id.editparent)
        editparent2 = findViewById(R.id.editparent2)
        calbutton = findViewById(R.id.calculate)
    }
}