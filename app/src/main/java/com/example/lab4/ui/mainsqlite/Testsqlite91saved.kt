package com.example.lab4.ui.mainsqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.R

class Testsqlite91saved : AppCompatActivity() {
    private lateinit var emoney: EditText
    private lateinit var eson: EditText
    private lateinit var eparent: EditText
    private lateinit var eparents: EditText
    private lateinit var eetc: EditText
    //private lateinit var txtResult: TextView
    //private lateinit var txtResults: TextView
    private lateinit var somrot: EditText
    private lateinit var calbutton: Button
    //private lateinit var bt_reset: Button
    private lateinit var TestSQLiteHelper: TestSQLiteHelper



    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sql91saved)

        initView()
        TestSQLiteHelper = TestSQLiteHelper(this)

        var buttonGoto = findViewById<Button>(R.id.bt_reset)
        buttonGoto!!.setOnClickListener {
            var intent = Intent(this, TestPageToView::class.java)
            startActivity(intent)
        }
        var txtResults = findViewById<TextView>(R.id.txtResults)
        var txtResult = findViewById<TextView>(R.id.txtResult)

        var v_money : Double = 0.0
        var v_son : Double = 0.0
        var v_parent : Double = 0.0
        var v_parents : Double = 0.0
        var v_etc : Double = 0.0
        var v_somrot : Double = 0.0
        var ans : Double = 0.0

        calbutton.setOnClickListener {
            val somrots = somrot.text.toString()
            if(somrots.isEmpty()){
                Toast.makeText(this, "กรุณาใส่ข้อมูล!", Toast.LENGTH_SHORT).show()
            }else{
                v_money = emoney.text.toString().toDouble()
                v_son = eson.text.toString().toDouble()
                v_parent = eparent.text.toString().toDouble()
                v_parents = eparents.text.toString().toDouble()
                v_somrot = somrot.text.toString().toDouble()
                v_etc = eetc.text.toString().toDouble()

                //getMoney
                v_money = v_money*12
                if(v_money >= 100000){
                    ans = v_money-160000
                }else{
                    v_money = (v_money*50)/100
                    ans = v_money-60000
                }
                //More
                if(v_parent > 0.0){
                    v_parent = v_parent*30000
                    ans = ans-v_parent
                }
                if(v_parents > 0.0){
                    v_parents = v_parents*30000
                    ans = ans-v_parents
                }
                if(v_son > 0.0){
                    v_son = v_son*30000
                    ans = ans-v_son
                }
                if(v_etc > 0.0){
                    ans = ans-v_etc
                }
                if(v_somrot == 1.0){
                    ans = ans-60000
                }
                Toast.makeText(this, "Values ANS after check :  "+ans, Toast.LENGTH_SHORT).show()
                //Process
                if(ans >= 5000001){
                    txtResult.setText("อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 35 %")
                    ans = ((ans-5000000)*0.35)+600000
                    txtResults.setText("ภาษีที่ต้องจ่าย : "+ans)
                }else if(ans >= 2000001 && ans <= 5000000){
                    txtResult.setText("อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 30 %")
                    ans = ((ans-2000000)*0.30)+250000
                    txtResults.setText("ภาษีที่ต้องจ่าย : "+ans)
                }else if(ans >= 1000001 && ans <= 2000000){
                    txtResult.setText("อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 25 %")
                    ans = ((ans-1000000)*0.25)+50000
                    txtResults.setText("ภาษีที่ต้องจ่าย : "+ans)
                }else if(ans >= 750001 && ans <= 1000000){
                    txtResult.setText("อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 20 %")
                    ans = ((ans-750000)*0.20)+37500
                    txtResults.setText("ภาษีที่ต้องจ่าย : "+ans)
                }else if(ans >= 500001 && ans <= 750000){
                    txtResult.setText("อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 15 %")
                    ans = ((ans-500000)*0.15)+20000
                    txtResults.setText("ภาษีที่ต้องจ่าย : "+ans)
                }else if(ans >= 300001 && ans <= 500000){
                    txtResult.setText("อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 10 %")
                    ans = ((ans-300000)*0.10)+7500
                    txtResults.setText("ภาษีที่ต้องจ่าย : "+ans)
                }else if(ans >= 150001 && ans <= 300000){
                    txtResult.setText("อัตราเงินได้บุคลลธรรมดาได้รับค่าลดหย่อน 5 %")
                    ans = ((ans-150000)*0.05)
                    txtResults.setText("ภาษีที่ต้องจ่าย : "+ans)
                }else{
                    ans = 0.0
                    txtResult.setText("ยกเว้น")
                    txtResults.setText("0")
                }
                //addStudent()
                //Testing

                val money = emoney.text.toString()
                if(money.isEmpty() /*|| son.isEmpty() || parent.isEmpty() || parents.isEmpty() || etc.isEmpty() || somrot.isEmpty()*/){
                    Toast.makeText(this, "Please enter requried field", Toast.LENGTH_SHORT).show()
                }else{
                    val std = TestModel(money = money, ans = ans.toString())
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
        //bt_reset.setOnClickListener { getStudents() }
    }

    /*private fun addStudent(){
        val money = emoney.text.toString()
        /*val son = eson.text.toString()
        val parent = eparent.text.toString()
        val parents = eparents.text.toString()
        val etc = eetc.text.toString()
        val somrot = somrot.text.toString()*/
        val ans:Int

        if(money.isEmpty() /*|| son.isEmpty() || parent.isEmpty() || parents.isEmpty() || etc.isEmpty() || somrot.isEmpty()*/){
            Toast.makeText(this, "Please enter requried field", Toast.LENGTH_SHORT).show()
        }else{
            val std = TestModel(money = money/*,son = son, parent = parent, parents = parents, etc = etc, somrot = somrot*/)
            val status = TestSQLiteHelper.insertStudent(std)

            if(status > -1){
                Toast.makeText(this, "Student Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
                getStudents()
            }else {
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }*/

    private fun clearEditText(){
        emoney.setText("")
        eson.setText("")
        eparent.setText("")
        eparents.setText("")
        eetc.setText("")
        somrot.setText("")
        emoney.requestFocus()
    }

    private fun initView(){
        emoney = findViewById(R.id.emoney)
        eson = findViewById(R.id.eson)
        eparent = findViewById(R.id.eparent)
        eparents = findViewById(R.id.eparents)
        eetc = findViewById(R.id.eetc)
        somrot = findViewById(R.id.somrot)
        calbutton = findViewById(R.id.bt_cal)
        //bt_reset = findViewById(R.id.bt_reset)
    }

}