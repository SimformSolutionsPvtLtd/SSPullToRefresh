package com.simform.demo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pulltorefresh.R
import com.simform.refresh.SSAnimationView
import com.simform.refresh.SSPullToRefreshLayout
import kotlinx.android.synthetic.main.activity_main.ssPullRefresh
import kotlinx.android.synthetic.main.activity_main.rv
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var adapter = RecyclerAdapter(this@MainActivity)

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "SSPullToRefresh"

        setUpRecyclerView()
        // set setOnRefreshListener on pull refresh view
        ssPullRefresh.setOnRefreshListener(object : SSPullToRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                GlobalScope.launch {
                    delay(5000)
                    runOnUiThread {
                        ssPullRefresh.setRefreshing(false)
                    }
                    MainScope().launch {
                        adapter.randomizeData()
                        Toast.makeText(this@MainActivity,"Refresh Complete",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        // set height and width of refresh view
        ssPullRefresh.setRefreshViewParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300))
        // set any lottie animation
        ssPullRefresh.setLottieAnimation("lottie_clock.json")
        // set repeat mode of lottie animation
        ssPullRefresh.setRepeatMode(SSPullToRefreshLayout.RepeatMode.REPEAT)
        // set number of times the animation repeats
        ssPullRefresh.setRepeatCount(SSPullToRefreshLayout.RepeatCount.INFINITE)
        //set style of RefreshLayout : NORMAL, FLOAT, PINNED
        ssPullRefresh.setRefreshStyle(SSPullToRefreshLayout.RefreshStyle.NORMAL)

        ssPullRefresh.setRefreshInitialOffset(100f)
        ssPullRefresh.setRefreshStyle(SSPullToRefreshLayout.RefreshStyle.FLOAT)
    }

    private fun setUpRecyclerView() {
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }
    
}