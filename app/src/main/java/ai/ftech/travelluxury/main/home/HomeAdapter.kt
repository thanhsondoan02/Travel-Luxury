package ai.ftech.travelluxury.main.home

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseAdapter
import ai.ftech.travelluxury.common.BaseVH
import ai.ftech.travelluxury.data.getCategoryData
import ai.ftech.travelluxury.main.myaccount.MyAccountAdapter
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class HomeAdapter : BaseAdapter() {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 0
            1,2 -> 1
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun getLayoutResource(viewType: Int): Int {
        return when (viewType) {
            0 -> R.layout.home_big_features_item
            1 -> R.layout.home_category_item
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun initData(): MutableList<Any> {
        return getCategoryData()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getViewHolder(inflateView: View, viewType: Int): BaseVH<Any> {
        return when (viewType) {
            0 -> BigFeaturesVH(inflateView) as BaseVH<Any>
            else -> CategoryVH(inflateView) as BaseVH<Any>
        }
    }


    class BigFeaturesVH(itemView: View) : BaseVH<BigFeaturesData>(itemView)

    class CategoryVH(itemView: View) : BaseVH<CategoryData>(itemView) {

        override fun onBind(data: CategoryData) {
            // set image background color
            itemView.findViewById<ImageView>(R.id.ivHomeCategoryImage).setBackgroundColor(Color.parseColor(data.imageBackgroundColor))
            // set image source
            itemView.findViewById<ImageView>(R.id.ivHomeCategoryImage).setImageResource(data.imageSource)
            // set title
            itemView.findViewById<TextView>(R.id.tvHomeCategoryTitle).text = data.title
            // set description
            itemView.findViewById<TextView>(R.id.tvHomeCategoryDescription).text = data.title
        }

    }

    class BigFeaturesData()

    data class CategoryData(
        val imageSource: Int,
        val imageBackgroundColor: String,
        val title: String,
        val description: String
    )
}