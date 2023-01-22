package com.example.lab4.ui.mainsqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.R

class TestAdapter : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    private var stdList: ArrayList<TestModel> = ArrayList()
    private var onClickItem: ((TestModel) -> Unit)? = null
    private var onClickDeleteItem: ((TestModel) -> Unit)? = null

    fun addItems(items: ArrayList<TestModel>) {
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnClickDeleteItem(callback: (TestModel) -> Unit) {
        this.onClickDeleteItem = callback
    }

    fun setOnClickItem(callback: (TestModel) -> Unit) {
        this.onClickItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TestViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.test_card_item_std, parent, false)
    )

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener { onClickItem?.invoke(std) }
        holder.del.setOnClickListener { onClickDeleteItem?.invoke(std) }
    }

    override fun getItemCount(): Int {
        return stdList.size

    }

    class TestViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.txt_id)
        private var money = view.findViewById<TextView>(R.id.txtt_money)
        private var ans = view.findViewById<TextView>(R.id.txtt_ans)
        var del = view.findViewById<TextView>(R.id.bt_del)

        fun bindView(std: TestModel) {
            id.text = std.id.toString()
            money.text = std.money
            ans.text = std.ans
        }

    }
}