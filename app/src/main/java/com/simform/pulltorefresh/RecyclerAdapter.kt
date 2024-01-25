package com.simform.pulltorefresh

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simform.pulltorefresh.databinding.ListItemBinding

class RecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.MyRecyclerViewHolder>() {

    private var items: ArrayList<Triple<String, String, Int>> = ArrayList()
    private var dataSet: ArrayList<Triple<String, String, Int>> = ArrayList()

    init {
        dataSet.apply {
            add(Triple("Thor", "Odinson", R.drawable.thor))
            add(Triple("Steve", "Rogers", R.drawable.cap))
            add(Triple("Elena", "Gilbert", R.drawable.elena))
            add(Triple("Demon", "Salvatore", R.drawable.demon))
            add(Triple("Stephan", "Salvatore", R.drawable.stehpan))
            add(Triple("Tony", "Stark", R.drawable.tony))
            add(Triple("Barney", "Stinson", R.drawable.barney))
            add(Triple("Ted", "Mosby", R.drawable.ted))
            add(Triple("Lightning", "Mcqueen", R.drawable.mc))
        }
        randomizeData()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        val lf = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(lf)
        return MyRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) =
        with(holder.binding) {
            textViewData.text = items[position].first
            textViewData2.text = items[position].second
            imageViewProfile.setImageBitmap(
                BitmapFactory.decodeResource(
                    context.resources, items[position].third
                )
            )
        }

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

    inner class MyRecyclerViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}