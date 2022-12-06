package com.simform.demo

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pulltorefresh.R
import com.example.pulltorefresh.databinding.ActivityMainBinding
import com.simform.refresh.SSPullToRefreshLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private var mAdapter = RecyclerAdapter(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        title = "SSPullToRefresh"

        with(mBinding) {
            setUpRecyclerView()
            // set setOnRefreshListener on pull refresh view
            ssPullRefresh.setOnRefreshListener {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(2000)
                    ssPullRefresh.setRefreshing(false) // This stops refreshing
                    mAdapter.randomizeData()
                    Toast.makeText(
                        this@MainActivity,
                        "Refresh Complete",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            // set height and width of refresh view
            ssPullRefresh.setRefreshViewParams(
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    300
                )
            )
            // set any lottie animation
            ssPullRefresh.setLottieAnimation("lottie_clock.json")
            // set repeat mode of lottie animation
            ssPullRefresh.setRepeatMode(SSPullToRefreshLayout.RepeatMode.REPEAT)
            // set number of times the animation repeats
            ssPullRefresh.setRepeatCount(SSPullToRefreshLayout.RepeatCount.INFINITE)
            //set style of RefreshLayout : NORMAL, FLOAT, PINNED
            ssPullRefresh.setRefreshStyle(SSPullToRefreshLayout.RefreshStyle.NORMAL)
        }
    }

    private fun setUpRecyclerView() = with(mBinding) {
        rv.layoutManager = LinearLayoutManager(this@MainActivity)
        rv.adapter = mAdapter
    }
}