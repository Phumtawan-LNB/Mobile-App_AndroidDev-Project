package com.example.lab4.ui.mainsqlite

import java.util.*

data class TestModel(
    var id: Int = getAutoID(),
    var money: String = "",
    var ans: String = ""
){
    companion object {
        fun getAutoID():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}

