package com.simform.refresh

interface RefreshCallbacks {
    /**
     * When the content view has reached to the start point and refresh has been completed, view will be reset.
     */
    fun reset()

    /**
     * Refresh View is refreshing
     */
    fun refreshing()

    /**
     * refresh has been completed
     */
    fun refreshComplete()

    /**
     * Refresh View is dropped down to the refresh point
     */
    fun pullToRefresh()

    /**
     * Refresh View is released into the refresh point
     */
    fun releaseToRefresh()

    /**
     * @param pullDistance The drop-down distance of the refresh View
     * @param pullProgress The drop-down progress of the refresh View and the pullProgress may be more than 1.0f
     * pullProgress = pullDistance / refreshTargetOffset
     */
    fun pullProgress(pullDistance: Float, pullProgress: Float)
}