package com.simform.demo

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.pulltorefresh.R
import com.simform.refresh.DefaultAnimationView
import com.simform.refresh.SSDragDistanceConverter
import com.simform.refresh.SSPullToRefreshLayout
import kotlinx.android.synthetic.main.activity_main.cpr
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
        cpr.setOnRefreshListener(object : SSPullToRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                GlobalScope.launch {
                    delay(3000)
                    cpr.setRefreshing(false)
                    MainScope().launch {
                        adapter.randomizeData()
                        Toast.makeText(this@MainActivity,"Refresh Complete",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        cpr.setDragDistanceConverter(SSDragDistanceConverter())
        cpr.setLottieAnimation("lottie_isometric-plane.json")
        cpr.setRefreshView(
            DefaultAnimationView(this),
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300)
        )
        cpr.setRepeatMode(LottieDrawable.RESTART)
        cpr.setRepeatCount(ValueAnimator.INFINITE)
        cpr.setRefreshStyle(SSPullToRefreshLayout.RefreshStyle.NORMAL)
    }

    private fun setUpRecyclerView() {
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }
    
}