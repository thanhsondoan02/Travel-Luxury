package ai.ftech.travelluxury.main.home

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.loadUrl
import ai.ftech.travelluxury.model.home.City
import ai.ftech.travelluxury.model.home.HomeModel.Companion.HOME_MODEL
import ai.ftech.travelluxury.model.hotellist.HotelListModel.Companion.HOTEL_LIST_MODEL
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CityHotelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), HomeContract.View {

    var cityList: List<City>? = listOf()
    var listener: Listener? = null
    private val presenter = HomePresenter(this)

    init {
        presenter.getHotelListApi()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val inflateView = View.inflate(parent.context, R.layout.end_of_section_item, null)
            EndVH(inflateView)
        } else {
            val inflateView =
                View.inflate(parent.context, R.layout.horizontal_list_square_item, null)
            CityVH(inflateView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CityVH) {
            holder.bind(cityList?.get(position) ?: return)
        }
    }

    override fun getItemCount(): Int {
        return (cityList?.size ?: 0) + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == cityList?.size) 1 else 0
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetHotelList(state: CITY_HOTEL_STATE, message: String) {
        when (state) {
            CITY_HOTEL_STATE.SUCCESS -> {
                this@CityHotelAdapter.cityList = HOME_MODEL.cityList
                notifyDataSetChanged()
            }
            CITY_HOTEL_STATE.FAILURE -> {
                Log.d(TAG, "onGetHotelList: $message")
            }
        }
    }

    inner class CityVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivCityImage = itemView.findViewById<ImageView>(R.id.ivHorizontalListSquareImage)

        fun bind(city: City) {
            ivCityImage.loadUrl(city.image)
            ivCityImage.setOnClickListener {
                listener?.onCityClick()

                HOTEL_LIST_MODEL.cityId = city.id
                HOTEL_LIST_MODEL.cityName = city.name
            }
        }
    }

    inner class EndVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Listener {
        fun onCityClick()
    }
}
