package com.example.lab4.ui.mainsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TestSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "student.db"
        private const val TBL_STUDENT = "tbl_student"
        private const val ID = "id"
        private const val MONEY = "money"
        private const val ANS = "ans"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent = ("CREATE TABLE " + TBL_STUDENT + "("
                + ID + " INTEGER PRIMARY KEY," + MONEY + " TEXT," + ANS + " TEXT" + ")")
        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_STUDENT")
        onCreate(db)
    }

    fun insertStudent(std: TestModel): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(MONEY, std.money)
        contentValues.put(ANS, std.ans)

        val success = db.insert(TBL_STUDENT, null, contentValues)
        db.close()
        return success
    }

    fun getALLStudent(): ArrayList<TestModel>{
        val stdList: ArrayList<TestModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_STUDENT"
        val db = this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var money: String
        var ans: String

        if(cursor.moveToFirst()) {
            do{
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                money = cursor.getString(cursor.getColumnIndexOrThrow("money"))
                ans = cursor.getString(cursor.getColumnIndexOrThrow("ans"))

                val std = TestModel(id = id, money = money, ans = ans)
                stdList.add(std)
            }while (cursor.moveToNext())
        }
        return stdList
    }
    fun deleteStudentById(id:Int): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID,id)

        val success = db.delete(TBL_STUDENT,"id=$id",null)
        db.close()
        return success

    }

}