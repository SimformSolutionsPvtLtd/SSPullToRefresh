package com.simform.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pulltorefresh.R
import com.simform.refresh.SSPullToRefreshLayout
import kotlinx.android.synthetic.main.activity_main.ssPullRefresh
import kotlinx.android.synthetic.main.activity_main.rv
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var adapter = RecyclerAdapter(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "SSPullToRefresh"

        setUpRecyclerView()
        ssPullRefresh.setOnRefreshListener(object : SSPullToRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                GlobalScope.launch {
                    delay(3000)
                    ssPullRefresh.setRefreshing(false)
                    MainScope().launch {
                        adapter.randomizeData()
                        Toast.makeText(this@MainActivity,"Refresh Complete",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        ssPullRefresh.setDragDistanceConverter(com.simform.refresh.SSDragDistanceConverter())
        ssPullRefresh.setLottieAnimation("lottie_isometric-plane.json")
        ssPullRefresh.setRefreshView(
            com.simform.refresh.DefaultAnimationView(this),
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300)
        )
        ssPullRefresh.setRepeatMode(SSPullToRefreshLayout.RepeatMode.REPEAT)
        ssPullRefresh.setRepeatCount(SSPullToRefreshLayout.RepeatCount.INFINITE)
        ssPullRefresh.setRefreshStyle(SSPullToRefreshLayout.RefreshStyle.NORMAL)
    }

    private fun setUpRecyclerView() {
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }
    
}