package com.example.lab4.ui.personal90

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lab4.R

class Personal90Fragment : Fragment() {

    private lateinit var homeViewModel: Personal90ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(Personal90ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_personal90, container, false)

        val editBonus = root.findViewById<EditText>(R.id.editbonus)
        val editMoney = root.findViewById<EditText>(R.id.editmoney)
        val editextra = root.findViewById<TextView>(R.id.editextra)
        val editchild = root.findViewById<TextView>(R.id.editchild)
        val editparent = root.findViewById<TextView>(R.id.editparent)
        val editparent2 = root.findViewById<TextView>(R.id.editparent2)
        val editextra2 = root.findViewById<TextView>(R.id.editextra2)
        val editspouse = root.findViewById<TextView>(R.id.editspouse)


        val calButton = root.findViewById<Button>(R.id.calculate)
        val resetButton = root.findViewById<Button>(R.id.bt_views)
        val resultRange = root.findViewById<TextView>(R.id.result)
        val resultRange2 = root.findViewById<TextView>(R.id.result2)

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



        calButton.setOnClickListener{
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


        }


        resetButton.setOnClickListener{
            resultRange2.text = ""
            resultRange.text = ""
            editMoney.setText("")
            editBonus.setText ("")
            editextra.setText ("")
            editextra2.setText ("")
            editchild.setText ("")
            editparent.setText ("")
            editparent2.setText ("")

        }

        return root
    }



}