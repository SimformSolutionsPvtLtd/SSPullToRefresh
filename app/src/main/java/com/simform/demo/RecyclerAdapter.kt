package com.simform.demo

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pulltorefresh.R
import kotlinx.android.synthetic.main.list_item.view.image_view_profile
import kotlinx.android.synthetic.main.list_item.view.text_view_data2
import kotlinx.android.synthetic.main.list_item.view.text_view_data

class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyRecyclerViewHolder>() {

    private var items: ArrayList<Triple<String,String,Int>> = ArrayList()
    private var dataSet: ArrayList<Triple<String,String,Int>> = ArrayList()
    init {
        dataSet.apply {
            add(Triple("Thor","Odinson", R.drawable.thor))
            add(Triple("Steve","Rogers",R.drawable.cap))
            add(Triple("Elena","Gilbert",R.drawable.elena))
            add(Triple("Demon","Salvatore",R.drawable.demon))
            add(Triple("Stephan","Salvatore",R.drawable.stehpan))
            add(Triple("Tony","Stark",R.drawable.tony))
            add(Triple("Barney","Stinson",R.drawable.barney))
            add(Triple("Ted","Mosby",R.drawable.ted))
            add(Triple("Lightning","Mcqueen",R.drawable.mc))
        }
        randomizeData()
    }

    inner class MyRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        holder.itemView.text_view_data.text = items[position].first
        holder.itemView.text_view_data2.text = items[position].second
        holder.itemView.image_view_profile.setImageBitmap(BitmapFactory.decodeResource(context.resources,items[position].third))
    }

    override fun getItemCount(): Int = items.size

    fun randomizeData() {
        items = ArrayList()
        items.apply {
            val n = (0..dataSet.size).random()
            for (i in 0..n) {
                val item = dataSet.random()
                if (!items.contains(item)) add(item)
            }
        }
        notifyDataSetChanged()
    }
}