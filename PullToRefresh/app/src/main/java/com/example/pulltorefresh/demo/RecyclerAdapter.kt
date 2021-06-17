package com.example.pulltorefresh.demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pulltorefresh.R
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyRecyclerViewHolder>() {

    private var items: ArrayList<Pair<String,String>> = ArrayList()

    inner class MyRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        holder.itemView.text_view_data.text = items[position].first
        holder.itemView.text_view_data2.text = items[position].second
    }

    override fun getItemCount(): Int = items.size

    fun loadData() {
        items.add(Pair("Thor","Odinson"))
        items.add(Pair("Steve","Rogers"))
        items.add(Pair("Elena","Gilbert"))
        items.add(Pair("Demon","Salvatore"))
        items.add(Pair("Stephan","Salvatore"))
        items.add(Pair("Tony","Stark"))
        items.add(Pair("Barney","Stinson"))
        items.add(Pair("Ted","Mosby"))
        items.add(Pair("Trevor","Phillips"))
        notifyDataSetChanged()
    }

}