package com.lixlop.nestedrv.rv

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.NestedScrollingParentHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * @author 小樓 on 2020/6/1
 */
class NestedRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs),
    NestedScrollingParent2 {
    private var parentHelper: NestedScrollingParentHelper? = null

    init {
        parentHelper = NestedScrollingParentHelper(this)
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        parentHelper?.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return true
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        //dy:下滑负数，上滑正数
        if (dy > 0 && canScrollVertically(1)) {
            scrollBy(0, dy)
        }

        if (dy < 0 && target is RecyclerView && !target.canScrollVertically(-1)) {
            scrollBy(0, dy)
        }
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        parentHelper?.onStopNestedScroll(target, type)
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        return false
    }
}