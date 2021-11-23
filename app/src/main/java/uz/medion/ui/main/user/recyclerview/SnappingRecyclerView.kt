package uz.medion.ui.main.user.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_FLING
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SnappingRecyclerView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    RecyclerView(context!!, attrs, defStyleAttr) {
    private var _userScrolling = false
    private var _scrolling = false
    private var _scrollState = SCROLL_STATE_IDLE
    private var _lastScrollTime: Long = 0
    private val mHandler: Handler = Handler()
    private var _scaleViews = false
    private var _orientation = Orientation.HORIZONTAL
    private var _childViewMetrics: ChildViewMetrics? = null
    private var _listener: OnViewSelectedListener? = null

    /**
     * Returns the currently centered item aka the selected item
     */
    var selectedPosition = 0
        private set

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    private fun init() {
        setHasFixedSize(true)
        setOrientation(_orientation)
        enableSnapping()
    }

    private var scrolling = false
    override fun onChildAttachedToWindow(child: View) {
        super.onChildAttachedToWindow(child)
        if (!scrolling && _scrollState == SCROLL_STATE_IDLE) {
            scrolling = true
            scrollToView(centerView)
            updateViews()
        }
    }

    private fun enableSnapping() {
        viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeGlobalOnLayoutListener(this)
            }
        })
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                updateViews()
                super.onScrolled(recyclerView, dx, dy)
            }

            @SuppressLint("SyntheticAccessor")
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                /** if scroll is caused by a touch (scroll touch, not any touch)  */
                if (newState == SCROLL_STATE_TOUCH_SCROLL) {
                    /** if scroll was initiated already, it would probably be a tap  */
                    /** if scroll was not initiated before, this is probably a user scrolling  */
                    if (!_scrolling) {
                        _userScrolling = true
                    }
                } else if (newState == SCROLL_STATE_IDLE) {
                    /** if user is the one scrolling, snap to the view closest to center  */
                    if (_userScrolling) {
                        scrollToView(centerView)
                    }
                    _userScrolling = false
                    _scrolling = false
                    /** if idle, always check location and correct it if necessary, this is just an extra check  */
                    if (centerView != null && getPercentageFromCenter(
                            centerView
                        ) > 0
                    ) {
                        scrollToView(centerView)
                    }
                    /** if idle, notify listeners of new selected view  */
                    notifyListener()
                } else if (newState == SCROLL_STATE_FLING) {
                    _scrolling = true
                }
                _scrollState = newState
            }
        })
    }

    private fun notifyListener() {
        val view: View? = centerView
        val position = getChildAdapterPosition(view!!)
        /** if there is a listener and the index is not the same as the currently selected position, notify listener  */
        if (_listener != null && position != selectedPosition) {
            _listener!!.onSelected(view, position)
        }
        selectedPosition = position
    }

    /**
     * Set the orientation for this SnappingRecyclerView
     * @param orientation LinearLayoutManager.HORIZONTAL or LinearLayoutManager.VERTICAL
     */
    fun setOrientation(orientation: Orientation) {
        _orientation = orientation
        _childViewMetrics = ChildViewMetrics(_orientation)
        layoutManager = LinearLayoutManager(context, _orientation.intValue(), false)
    }

    /**
     * Set the OnViewSelectedListener
     * @param listener the OnViewSelectedListener
     */
    fun setOnViewSelectedListener(listener: OnViewSelectedListener?) {
        _listener = listener
    }

    /**
     * Enable downscaling of views which are not focused, based on how far away they are from the center
     * @param enabled enable or disable the scaling behaviour
     */
    fun enableViewScaling(enabled: Boolean) {
        _scaleViews = enabled
    }

    internal fun updateViews() {
        for (i in 0 until childCount) {
            val child: View = getChildAt(i)
            setMarginsForChild(child)
            if (_scaleViews) {
                val percentage = getPercentageFromCenter(child)
                val scale = 1f - 0.7f * percentage
                child.setScaleX(scale)
                child.setScaleY(scale)
            }
        }
    }

    /**
     * Adds the margins to a childView so a view will still center even if it's only a single child
     * @param child childView to set margins for
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun setMarginsForChild(child: View) {
        val lastItemIndex = layoutManager!!.itemCount - 1
        val childIndex = getChildAdapterPosition(child)
        var startMargin = 0
        var endMargin = 0
        var topMargin = 0
        var bottomMargin = 0
        if (_orientation == Orientation.VERTICAL) {
            topMargin = if (childIndex == 0) centerLocation else 0
            bottomMargin = if (childIndex == lastItemIndex) centerLocation else 0
        } else {
            startMargin = if (childIndex == 0) centerLocation else 0
            endMargin = if (childIndex == lastItemIndex) centerLocation else 0
        }
        /** if sdk minimum level is 17, set RTL margins  */
        if (_orientation == Orientation.HORIZONTAL && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            (child.getLayoutParams() as MarginLayoutParams).marginStart = startMargin
            (child.getLayoutParams() as MarginLayoutParams).marginEnd = endMargin
        }
        /** If layout direction is RTL, swap the margins   */
        if (ViewCompat.getLayoutDirection(child) == ViewCompat.LAYOUT_DIRECTION_RTL) {
            (child.getLayoutParams() as MarginLayoutParams).setMargins(
                endMargin,
                topMargin,
                startMargin,
                bottomMargin
            )
        } else {
            (child.getLayoutParams() as MarginLayoutParams).setMargins(
                startMargin,
                topMargin,
                endMargin,
                bottomMargin
            )
        }
        /** if sdk minimum level is 18, check if view isn't undergoing a layout pass (this improves the feel of the view by a lot)  */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (!child.isInLayout()) child.requestLayout()
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val currentTime = System.currentTimeMillis()
        /** if touch events are being spammed, this is due to user scrolling right after a tap,
         * so set userScrolling to true  */
        if (_scrolling && _scrollState == SCROLL_STATE_TOUCH_SCROLL) {
            if (currentTime - _lastScrollTime < MINIMUM_SCROLL_EVENT_OFFSET_MS) {
                _userScrolling = true
            }
        }
        _lastScrollTime = currentTime
        val location = if (_orientation == Orientation.VERTICAL) event.y
            .toInt() else event.x.toInt()
        val targetView: View? = getChildClosestToLocation(location)
        if (!_userScrolling) {
            if (event.action == MotionEvent.ACTION_UP) {
                if (targetView !== centerView) {
                    scrollToView(targetView)
                    return true
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        val location = if (_orientation == Orientation.VERTICAL) event.y
            .toInt() else event.x.toInt()
        val targetView: View? = getChildClosestToLocation(location)
        return if (targetView !== centerView) {
            true
        } else super.onInterceptTouchEvent(event)
    }

    override fun scrollToPosition(position: Int) {
        _childViewMetrics!!.size(getChildAt(0))
        smoothScrollBy(_childViewMetrics!!.size(getChildAt(0)) * position)
    }

    private fun getChildClosestToLocation(location: Int): View? {
        if (childCount <= 0) return null
        var closestPos = 9999
        var closestChild: View? = null
        for (i in 0 until childCount) {
            val child: View = getChildAt(i)
            val childCenterLocation = _childViewMetrics!!.center(child).toInt()
            val distance = childCenterLocation - location
            /** if child center is closer than previous closest, set it as closest child   */
            if (Math.abs(distance) < Math.abs(closestPos)) {
                closestPos = distance
                closestChild = child
            }
        }
        return closestChild
    }

    /**
     * Check if the view is correctly centered (allow for 10px offset)
     * @param child the child view
     * @return true if correctly centered
     */
    private fun isChildCorrectlyCentered(child: View): Boolean {
        val childPosition = _childViewMetrics!!.center(child).toInt()
        return childPosition > centerLocation - 10 && childPosition < centerLocation + 10
    }

    internal val centerView: View?
        get() = getChildClosestToLocation(centerLocation)

    internal fun scrollToView(child: View?) {
        if (child == null) return
        stopScroll()
        val scrollDistance = getScrollDistance(child)
        if (scrollDistance != 0) smoothScrollBy(scrollDistance)
    }

    private fun getScrollDistance(child: View): Int {
        val childCenterLocation = _childViewMetrics!!.center(child).toInt()
        return childCenterLocation - centerLocation
    }

    private fun getPercentageFromCenter(child: View?): Float {
        val center = centerLocation.toFloat()
        val childCenter = _childViewMetrics!!.center(child)
        val offSet = Math.max(center, childCenter) - Math.min(center, childCenter)
        val maxOffset = center + _childViewMetrics!!.size(child)
        return offSet / maxOffset
    }

    private val centerLocation: Int
        get() = if (_orientation == Orientation.VERTICAL) measuredHeight / 2 else measuredWidth / 2

    fun smoothScrollBy(distance: Int) {
        if (_orientation == Orientation.VERTICAL) {
            super.smoothScrollBy(0, distance)
            return
        }
        super.smoothScrollBy(distance, 0)
    }

    fun scrollBy(distance: Int) {
        if (_orientation == Orientation.VERTICAL) {
            super.scrollBy(0, distance)
            return
        }
        super.scrollBy(distance, 0)
    }

    private fun scrollTo(position: Int) {
        val currentScroll = scrollOffset
        scrollBy(position - currentScroll)
    }

    val scrollOffset: Int
        get() = if (_orientation == Orientation.VERTICAL) computeVerticalScrollOffset() else computeHorizontalScrollOffset()

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mHandler.removeCallbacksAndMessages(null)
    }

    private class ChildViewMetrics(private val _orientation: Orientation) {
        fun size(view: View?): Int {
            return if (_orientation == Orientation.VERTICAL) view?.getHeight()!! else view!!.getWidth()
        }

        fun location(view: View?): Float {
            return if (_orientation == Orientation.VERTICAL) view!!.getY() else view!!.getX()
        }

        fun center(view: View?): Float {
            return location(view) + size(view) / 2
        }
    }

    enum class Orientation(var value: Int) {
        HORIZONTAL(LinearLayout.HORIZONTAL), VERTICAL(LinearLayout.VERTICAL);

        fun intValue(): Int {
            return value
        }
    }

    interface OnViewSelectedListener {
        fun onSelected(view: View?, position: Int)
    }

    companion object {
        private const val MINIMUM_SCROLL_EVENT_OFFSET_MS = 20
    }

    init {
        init()
    }
}