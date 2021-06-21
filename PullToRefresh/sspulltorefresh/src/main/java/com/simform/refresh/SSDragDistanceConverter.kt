package com.simform.refresh

import kotlin.math.abs
import kotlin.math.pow

class SSDragDistanceConverter {
    /**
     * @param scrollDistance the distance between the ACTION_DOWN point and the ACTION_MOVE point
     * @param refreshDistance the distance between the refresh point and the start point
     * @return the real distance of the refresh view moved
     */
    fun convert(scrollDistance: Float, refreshDistance: Float): Float {
        val originalDragPercent = scrollDistance / refreshDistance
        val dragPercent = 1.0f.coerceAtMost(abs(originalDragPercent))
        val extraOS = abs(scrollDistance) - refreshDistance
        val tensionSlingshotPercent = 0f.coerceAtLeast(extraOS.coerceAtMost(refreshDistance * 2.0f) / refreshDistance)
        val tensionPercent = (tensionSlingshotPercent / 4 - (tensionSlingshotPercent / 4).toDouble().pow(2.0)).toFloat() * 2f
        val extraMove = refreshDistance * tensionPercent * 2
        val convertY = (refreshDistance * dragPercent + extraMove).toInt()
        return convertY.toFloat()
    }

}