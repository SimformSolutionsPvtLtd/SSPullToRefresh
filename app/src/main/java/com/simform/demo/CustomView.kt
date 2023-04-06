package com.simform.demo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.example.pulltorefresh.databinding.LayoutBinding
import com.simform.refresh.RefreshCallbacks

class CustomView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs), RefreshCallbacks {

    private var binding: LayoutBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = LayoutBinding.inflate(inflater, this, true)
        binding.button.setOnClickListener {
            Toast.makeText(context, "Loading Action", Toast.LENGTH_SHORT).show()
        }
    }

    override fun reset() {
    }

    override fun refreshing() {
    }

    override fun refreshComplete() {
    }

    override fun pullToRefresh() {
    }

    override fun releaseToRefresh() {
    }

    override fun pullProgress(pullDistance: Float, pullProgress: Float) {
    }
}