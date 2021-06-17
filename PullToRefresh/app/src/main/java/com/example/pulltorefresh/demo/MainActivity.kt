package com.example.pulltorefresh.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pulltorefresh.R
import com.example.pulltorefresh.tmp.CustomPullRefresh
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "SSPullToRefresh"

        setUpRecyclerView()
        cpr.setOnRefreshListener(object : CustomPullRefresh.OnRefreshListener {
            override fun onRefresh() {
                GlobalScope.launch {
                    delay(3000)
                    cpr.setRefreshing(false)
                    MainScope().launch {
                        Toast.makeText(this@MainActivity,"Refresh Complete",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        cpr.setDragDistanceConverter(DemoDragDistanceConverter())
        val lottieView = DemoLottieView(this)
        cpr.setRefreshView(
            lottieView,
            ViewGroup.LayoutParams(300, 300)
        )
        cpr.setLottieAnimation("lottie_isometric-plane.json")
    }

    private fun setUpRecyclerView() {
        val adapter = RecyclerAdapter(this@MainActivity)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        adapter.loadData()
    }
}