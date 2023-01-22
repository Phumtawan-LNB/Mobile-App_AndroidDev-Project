package com.example.lab4.ui.mainsqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.R

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var stdList: ArrayList<StudentModel> = ArrayList()
    private var onClickItem: ((StudentModel) -> Unit)? = null
    private var onClickDeleteItem: ((StudentModel) -> Unit)? = null

    fun addItems(items: ArrayList<StudentModel>) {
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnClickDeleteItem(callback: (StudentModel) -> Unit) {
        this.onClickDeleteItem = callback
    }

    fun setOnClickItem(callback: (StudentModel) -> Unit) {
        this.onClickItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StudentViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_item_std, parent, false)
    )

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener { onClickItem?.invoke(std) }
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(std) }
    }

    override fun getItemCount(): Int {
        return stdList.size

    }

    class StudentViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.txt_id)
        private var name = view.findViewById<TextView>(R.id.txtt_money)
        private var email = view.findViewById<TextView>(R.id.txtt_ans)
        var btnDelete = view.findViewById<TextView>(R.id.bt_del)

        fun bindView(std: StudentModel) {
            id.text = std.id.toString()
            name.text = std.name
            email.text = std.email
        }

    }
}