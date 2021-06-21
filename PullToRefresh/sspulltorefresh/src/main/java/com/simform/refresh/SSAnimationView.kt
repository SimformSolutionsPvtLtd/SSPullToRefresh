package com.simform.refresh

import android.content.Context
import com.airbnb.lottie.LottieAnimationView

abstract class SSAnimationView(context: Context): LottieAnimationView(context) {
    /**
     * When the content view has reached to the start point and refresh has been completed, view will be reset.
     */
    abstract fun reset()

    /**
     * Refresh View is refreshing
     */
    abstract fun refreshing()

    /**
     * refresh has been completed
     */
    abstract fun refreshComplete()

    /**
     * Refresh View is dropped down to the refresh point
     */
    abstract fun pullToRefresh()

    /**
     * Refresh View is released into the refresh point
     */
    abstract fun releaseToRefresh()

    /**
     * @param pullDistance The drop-down distance of the refresh View
     * @param pullProgress The drop-down progress of the refresh View and the pullProgress may be more than 1.0f
     * pullProgress = pullDistance / refreshTargetOffset
     */
    abstract fun pullProgress(pullDistance: Float, pullProgress: Float)
}