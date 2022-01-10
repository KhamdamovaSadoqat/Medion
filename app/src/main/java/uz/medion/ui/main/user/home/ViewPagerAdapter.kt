package uz.medion.ui.main.user.home

import android.content.Context
import android.util.Log
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.HomeItem
import uz.medion.databinding.ItemCaruselBinding
import uz.medion.databinding.ItemHomeBinding
import uz.medion.utils.ImageDownloader

class ViewPagerAdapter(var list: List<String>, var context: Context) : PagerAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val binding =
            DataBindingUtil.inflate<ItemCaruselBinding>(inflater, R.layout.item_carusel, container, false)
        ImageDownloader.loadImage(context, list[position], binding.ivCarusel)
        container.addView(binding.root, 0)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}