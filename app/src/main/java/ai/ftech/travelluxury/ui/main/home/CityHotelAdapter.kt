package ai.ftech.travelluxury.ui.main.home

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.loadUrl
import ai.ftech.travelluxury.data.model.home.City
import ai.ftech.travelluxury.data.model.hotellist.HotelListModel
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CityHotelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var cityList: List<City>? = listOf()
    var listener: Listener? = null
    var isSuccess = false

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
        return if (isSuccess) {
            (cityList?.size ?: 0) + 1
        } else {
            0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == cityList?.size) 1 else 0
    }

    inner class CityVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivCityImage = itemView.findViewById<ImageView>(R.id.ivHorizontalListSquareImage)

        fun bind(city: City) {
            ivCityImage.loadUrl(city.image!!)
            ivCityImage.setOnClickListener {
                listener?.onCityClick()

                HotelListModel.INSTANCE.cityId = city.id
                HotelListModel.INSTANCE.cityName = city.name
            }
        }
    }

    inner class EndVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Listener {
        fun onCityClick()
    }
}
