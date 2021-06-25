package com.simform.refresh

import android.content.Context

class DefaultAnimationView(context: Context): SSLottieAnimationView(context) {

    override fun reset() {
        cancelAnimation()
        playAnimation()
    }

    override fun refreshing() {
    }

    override fun refreshComplete() {
        cancelAnimation()
    }

    override fun pullToRefresh() {
        playAnimation()
    }

    override fun releaseToRefresh() {
    }

    override fun pullProgress(pullDistance: Float, pullProgress: Float) {
    }

}