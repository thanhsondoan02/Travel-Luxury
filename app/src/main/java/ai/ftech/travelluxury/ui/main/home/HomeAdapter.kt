package ai.ftech.travelluxury.ui.main.home

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseAdapter
import ai.ftech.travelluxury.common.BaseVH
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.getCategoryData
import ai.ftech.travelluxury.data.model.home.City
import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter : BaseAdapter() {

    var listener: HomeFragment.IListener? = null
    var cityListVH: CityListVH? = null

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
            21, 23 -> HorizontalListVH(inflateView, viewType) as BaseVH<Any>
            22 -> {
                cityListVH = CityListVH(inflateView)
                return cityListVH as BaseVH<Any>
            }
            3 -> DoubleButtonVH(inflateView) as BaseVH<Any>
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onGetHotelCityListSuccess() {
        cityListVH?.adapter?.isSuccess = true
        cityListVH?.hideLoading()
        Log.d(TAG, "onGetHotelCityListSuccess: hid loading")
        cityListVH?.adapter?.notifyDataSetChanged()
    }

    class BigFeaturesVH(itemView: View) : BaseVH<BigFeaturesData>(itemView)

    class CategoryVH(itemView: View) : BaseVH<CategoryData>(itemView) {

        override fun onBind(data: CategoryData) {
            // set image background color
            itemView.findViewById<ImageView>(R.id.ivHomeCategoryImage)
                .setBackgroundColor(Color.parseColor(data.imageBackgroundColor))
            // set image source
            itemView.findViewById<ImageView>(R.id.ivHomeCategoryImage)
                .setImageResource(data.imageSource)
            // set title
            itemView.findViewById<TextView>(R.id.tvHomeCategoryTitle).text = data.title
            // set description
            itemView.findViewById<TextView>(R.id.tvHomeCategoryDescription).text = data.title
        }

    }

    inner class HorizontalListVH(itemView: View, viewType: Int) : BaseVH<FlightListData>(itemView) {

        private val rvHorizontal = itemView.findViewById<RecyclerView>(R.id.rvHorizontalList)
        private val llLoading: LinearLayout = itemView.findViewById(R.id.llHorizontalListLoading)

        private val type = when (viewType) {
            21 -> HorizontalListAdapter.ListType.FLIGHT
            23 -> HorizontalListAdapter.ListType.HOTEL_PROMOS
            else -> throw IllegalArgumentException("Invalid view type $viewType")
        }

        init {
            llLoading.visibility = View.GONE
            rvHorizontal.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            rvHorizontal.adapter = HorizontalListAdapter(type)
        }
    }

    inner class CityListVH(itemView: View) : BaseVH<City>(itemView) {

        var adapter = CityHotelAdapter().apply {
            this.listener = object : CityHotelAdapter.Listener {
                override fun onCityClick() {
                    this@HomeAdapter.listener?.onCityClick()
                }
            }
        }

        private val rvHorizontal = itemView.findViewById<RecyclerView>(R.id.rvHorizontalList)
        private val llLoading = itemView.findViewById<View>(R.id.llHorizontalListLoading)

        init {
            rvHorizontal.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            rvHorizontal.adapter = adapter

            showLoading()
        }

        fun showLoading() {
            llLoading.visibility = View.VISIBLE
            rvHorizontal.visibility = View.GONE
        }

        fun hideLoading() {
            llLoading.visibility = View.GONE
            rvHorizontal.visibility = View.VISIBLE
        }
    }

    inner class DoubleButtonVH(itemView: View) : BaseVH<DoubleButtonData>(itemView) {

        private val btnDomestic: Button = itemView.findViewById(R.id.btnHomeDoubleButtonDomestic)
        private val btnInternational: Button =
            itemView.findViewById(R.id.btnHomeDoubleButtonInternational)

        private var isDomestic = true

        init {
            updateDoubleButton()

            btnDomestic.setOnClickListener {
                if (!isDomestic) {
                    isDomestic = !isDomestic
                    updateDoubleButton()

                    listener!!.onDomesticClick()
                    cityListVH?.showLoading()
                }
            }
            btnInternational.setOnClickListener {
                if (isDomestic) {
                    isDomestic = !isDomestic
                    updateDoubleButton()

                    listener!!.onInternationalClick()
                    cityListVH?.showLoading()
                }
            }
        }

        private fun updateDoubleButton() {
            val white = ContextCompat.getColor(itemView.context, R.color.white)
            val blue = ContextCompat.getColor(itemView.context, R.color.main_blue_color)

            if (isDomestic) {
                btnDomestic.setTextColor(white)
                btnDomestic.setBackgroundColor(blue)
                btnInternational.setTextColor(blue)
                btnInternational.setBackgroundColor(white)
            } else {
                btnDomestic.setTextColor(blue)
                btnDomestic.setBackgroundColor(white)
                btnInternational.setTextColor(white)
                btnInternational.setBackgroundColor(blue)
            }
        }

    }

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
