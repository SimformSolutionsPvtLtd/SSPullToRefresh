package com.simform.refresh

import android.view.View

/**
 * Defines the coordinates of the view in its window
 *
 * @return  Pair of [Int] representing x and y
 */
val View.windowCoordinates: Pair<Int, Int>
    get() {
        val location = IntArray(2)
        getLocationInWindow(location)
        val x = location[0]
        val y = location[1]
        return x to y
    }

/**
 * Check if view bounds include the given point in window
 *
 * @param   x   The x coordinate of the point
 * @param   y   The y coordinate of the point
 *
 * @return  True if the view contains given point, false otherwise
 */
fun View.hasPoint(x: Int, y: Int): Boolean {
    val viewCoordinates = windowCoordinates
    return x in viewCoordinates.first..(viewCoordinates.first + width) &&
            y in viewCoordinates.second..(viewCoordinates.second + height)
}