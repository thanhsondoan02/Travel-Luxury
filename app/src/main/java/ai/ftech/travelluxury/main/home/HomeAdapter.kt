package ai.ftech.travelluxury.main.home

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseAdapter
import ai.ftech.travelluxury.common.BaseVH
import ai.ftech.travelluxury.data.getCategoryData
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter : BaseAdapter() {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 0
            1 -> 1
            2 -> 21
            3 -> 1
            4 -> 23
            5 -> 3
            6 -> 22
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun getLayoutResource(viewType: Int): Int {
        return when (viewType) {
            0 -> R.layout.home_big_features_item
            1 -> R.layout.home_category_item
            21, 22, 23 -> R.layout.horizontal_list_item
            3 -> R.layout.home_double_button_item
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
            1 -> CategoryVH(inflateView) as BaseVH<Any>
            21, 22, 23 -> HorizontalListVH(inflateView) as BaseVH<Any>
            3 -> DoubleButtonVH(inflateView) as BaseVH<Any>
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<Any> {
        return if (viewType == 21 || viewType == 22 || viewType == 23) {
            val type = when (viewType) {
                21 -> HorizontalListAdapter.ListType.FLIGHT
                22 -> HorizontalListAdapter.ListType.HOTEL
                23 -> HorizontalListAdapter.ListType.HOTEL_PROMOS
                else -> throw IllegalArgumentException("Invalid view type")
            }

            val inflateView = View.inflate(parent.context, getLayoutResource(viewType), null)

            val recyclerView = inflateView.findViewById<RecyclerView>(R.id.rvHorizontalList)
            recyclerView.layoutManager =
                LinearLayoutManager(parent.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = HorizontalListAdapter(type)

            getViewHolder(inflateView, viewType)
        } else {
            super.onCreateViewHolder(parent, viewType)
        }
    }

    class BigFeaturesVH(itemView: View) : BaseVH<BigFeaturesData>(itemView)

    class CategoryVH(itemView: View) : BaseVH<CategoryData>(itemView) {

        override fun onBind(data: CategoryData) {
            // set image background color
            itemView.findViewById<ImageView>(R.id.ivHomeCategoryImage).setBackgroundColor(Color.parseColor(data.imageBackgroundColor))
            // set image source
            itemView.findViewById<ImageView>(R.id.ivHomeCategoryImage)
                .setImageResource(data.imageSource)
            // set title
            itemView.findViewById<TextView>(R.id.tvHomeCategoryTitle).text = data.title
            // set description
            itemView.findViewById<TextView>(R.id.tvHomeCategoryDescription).text = data.title
        }

    }

    class HorizontalListVH(itemView: View) : BaseVH<FlightListData>(itemView)

    class DoubleButtonVH(itemView: View) : BaseVH<DoubleButtonData>(itemView)

    class BigFeaturesData

    data class CategoryData(
        val imageSource: Int,
        val imageBackgroundColor: String,
        val title: String,
        val description: String
    )

    class FlightListData

    class DoubleButtonData

}
