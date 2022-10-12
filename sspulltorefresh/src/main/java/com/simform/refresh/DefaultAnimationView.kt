package com.simform.refresh

import android.content.Context

class DefaultAnimationView(context: Context): SSLottieAnimationView(context) {

    private var mIsPlaying = false

    override fun reset() {
        mIsPlaying = false
        cancelAnimation()
    }

    override fun refreshing() {
    }

    override fun refreshComplete() {
        mIsPlaying = false
        cancelAnimation()
    }

    override fun pullToRefresh() {
    }

    override fun releaseToRefresh() {
    }

    override fun pullProgress(pullDistance: Float, pullProgress: Float) {
        if (!mIsPlaying) {
            mIsPlaying = true
            playAnimation()
        }
    }

}