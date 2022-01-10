package uz.medion.ui.main.user.home

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import uz.medion.R

class ViewPagerAdapter(var list: List<Int>, var ctx: Context) : PagerAdapter() {

    lateinit var layoutInflater: LayoutInflater
    lateinit var context: Context
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(R.layout.item_carusel, container, false)
        val img = view.findViewById<ImageView>(R.id.iv_carusel)
        img.setImageResource(list[position])
        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}