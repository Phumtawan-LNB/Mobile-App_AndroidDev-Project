package com.example.lab4.ui.personal91

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
import com.example.lab4.ui.personal90.Personal90ViewModel

class Personal91Fragment: Fragment() {
    private lateinit var homeViewModel: Personal90ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(Personal90ViewModel::class.java)
        var root = inflater.inflate(R.layout.fragment_personal91, container, false)
        var calbutton = root.findViewById<Button>(R.id.bt_cal)
        var money = root.findViewById<EditText>(R.id.emoney)
        var son = root.findViewById<EditText>(R.id.eson)
        var parent = root.findViewById<EditText>(R.id.eparent)
        var parents = root.findViewById<EditText>(R.id.eparents)
        var etc = root.findViewById<EditText>(R.id.eetc)
        var txtResult = root.findViewById<TextView>(R.id.txtResult)
        var txtResults = root.findViewById<TextView>(R.id.txtResults)
        var resetButton = root.findViewById<Button>(R.id.bt_reset)
        var somrot = root.findViewById<EditText>(R.id.somrot)

        var v_money : Double = 0.0
        var v_son : Double = 0.0
        var v_parent : Double = 0.0
        var v_parents : Double = 0.0
        var v_etc : Double = 0.0
        var v_somrot : Double = 0.0
        var ans : Double = 0.0

        calbutton.setOnClickListener {
            v_money = money.text.toString().toDouble()
            v_son = son.text.toString().toDouble()
            v_parent = parent.text.toString().toDouble()
            v_parents = parents.text.toString().toDouble()
            v_somrot = somrot.text.toString().toDouble()
            v_etc = etc.text.toString().toDouble()

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
                txtResult.setText("บุคคลมีรายได้ 0 - 150000 ได้รับการยกเว้น")
            }
        }
        resetButton.setOnClickListener {
            money.setText("")
            son.setText("")
            parent.setText("")
            parents.setText("")
            etc.setText("")
            txtResult.setText("")
            txtResults.setText("")
            somrot.setText("")
        }
        return root
    }
}