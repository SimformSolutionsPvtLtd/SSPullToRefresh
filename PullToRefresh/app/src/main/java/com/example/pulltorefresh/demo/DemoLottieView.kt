package com.example.pulltorefresh.demo

import android.animation.ValueAnimator
import android.content.Context
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.pulltorefresh.tmp.IRefreshStatus


class DemoLottieView(context: Context): LottieAnimationView(context), IRefreshStatus {

    private var mHasTriggeredRefresh = false

    init {
        repeatCount = ValueAnimator.INFINITE
        repeatMode = LottieDrawable.RESTART
    }

    override fun reset() {
        mHasTriggeredRefresh = false
        cancelAnimation()
    }

    override fun refreshing() {
        mHasTriggeredRefresh = true
        playAnimation()
    }

    override fun refreshComplete() {

    }

    override fun pullToRefresh() {
    }

    override fun releaseToRefresh() {

    }

    override fun pullProgress(pullDistance: Float, pullProgress: Float) {
        if (!mHasTriggeredRefresh) {
            val swipeProgress = 1.0f.coerceAtMost(pullProgress)
            refreshing()
        }
    }
}