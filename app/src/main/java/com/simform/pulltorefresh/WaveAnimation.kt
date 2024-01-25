package com.simform.pulltorefresh

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.simform.refresh.SSAnimationView
import kotlin.math.sin

// This is a demo view in which user can set any animation or
// just make it blank and set Gif animation using setGifAnimation() method on SSPullToRefreshLayout
class WaveAnimation(context: Context): SSAnimationView(context) {

    private var amplitude = 22f.toDp() // scale
    private var speed = 0f
    private val path = Path()
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var animator: ValueAnimator? = null

    override fun onDraw(c: Canvas) = c.drawPath(path, paint)

    private fun createAnimator(): ValueAnimator {
        return ValueAnimator.ofFloat(0f, Float.MAX_VALUE).apply {
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                speed -= WAVE_SPEED
                createPath()
                invalidate()
            }
        }
    }

    private fun createPath() {
        path.reset()
        paint.color = Color.parseColor("#203354")
        path.moveTo(0f, height.toFloat())
        path.lineTo(0f, amplitude)
        path.lineTo(0f, amplitude - 10)
        var i = 0
        while (i < width + 10) {
            val wx = i.toFloat()
            val sinComponent = sin((i + 10) * Math.PI / WAVE_AMOUNT_ON_SCREEN + speed).toFloat()
            val wy = amplitude * (2 + sinComponent)
            path.lineTo(wx, wy)
            i += 10
        }
        path.lineTo(width.toFloat(), height.toFloat())
        path.close()
    }

    override fun onDetachedFromWindow() {
        animator?.cancel()
        super.onDetachedFromWindow()
    }

    companion object {
        const val WAVE_SPEED = 0.25f
        const val WAVE_AMOUNT_ON_SCREEN = 350
    }

    private fun Float.toDp() = this * context.resources.displayMetrics.density


    override fun reset() {
    }

    override fun refreshing() {
    }

    override fun refreshComplete() {
        animator?.cancel()
    }

    override fun pullToRefresh() {
        animator = createAnimator().apply {
            start()
        }
    }

    override fun releaseToRefresh() {
    }

    override fun pullProgress(pullDistance: Float, pullProgress: Float) {
    }

}