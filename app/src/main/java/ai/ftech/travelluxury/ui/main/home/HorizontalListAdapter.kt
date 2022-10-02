package ai.ftech.travelluxury.ui.main.home

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseAdapter
import ai.ftech.travelluxury.common.BaseVH
import ai.ftech.travelluxury.data.getFlightList
import ai.ftech.travelluxury.data.getHotelPromosList
import ai.ftech.travelluxury.data.loadUrl
import ai.ftech.travelluxury.data.model.home.City
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class HorizontalListAdapter(val type: ListType) : BaseAdapter() {

//    private val cityHotelList: MutableList<City> = mutableListOf()

    init {
//        if (HOME_MODEL.cityHotelList == null) {
//            Log.d(TAG, "HorizontalListAdapter: HOME_MODEL.cityHotelList is null")
//        } else {
//            cityHotelList.addAll(HOME_MODEL.cityHotelList!!)
//            cityHotelList.add(City(-1, "", ""))
//        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getLayoutResource(viewType: Int): Int {
        return when (type) {
            ListType.FLIGHT -> R.layout.horizontal_list_square_bottom_text_item
            ListType.HOTEL_PROMOS -> R.layout.horizontal_list_rectangle_item
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun initData(): MutableList<Any> {
        return when (type) {
            ListType.FLIGHT -> getFlightList()
            ListType.HOTEL_PROMOS -> getHotelPromosList()
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getViewHolder(inflateView: View, viewType: Int): BaseVH<Any> {
        return when (type) {
            ListType.FLIGHT -> FlightElementVH(inflateView) as BaseVH<Any>
            ListType.HOTEL_PROMOS -> HotelPromosElementVH(inflateView) as BaseVH<Any>
        }
    }

    class FlightElementVH(itemView: View) : BaseVH<FlightData>(itemView) {
        override fun onBind(data: FlightData) {
            itemView.findViewById<ImageView>(R.id.ivHorizontalListSquareBottomTextImage)
                .setImageResource(data.imageSrc)
            itemView.findViewById<TextView>(R.id.tvHorizontalListSquareBottomTextPlace).text =
                data.place
            itemView.findViewById<TextView>(R.id.tvHorizontalListSquareBottomTextDate).text =
                data.date
            itemView.findViewById<TextView>(R.id.tvHorizontalListSquareBottomTextPrice).text =
                data.price
        }
    }

    class HotelElementVH(itemView: View) : BaseVH<City>(itemView) {

        private val ivCityImage = itemView.findViewById<ImageView>(R.id.ivHorizontalListSquareImage)
        private val ivTick = itemView.findViewById<ImageView>(R.id.ivHorizontalListSquareTick)
        private val tvEndOfSection =
            itemView.findViewById<TextView>(R.id.tvHorizontalListSquareEndOfSection)

        override fun onBind(data: City) {
            if (data.id == -1) { // End of section
                ivTick.setImageResource(R.drawable.ic_tick)
                tvEndOfSection.text = itemView.context.resources.getText(R.string.end_of_section)
            } else {

                ivCityImage.loadUrl(data.image)
            }
        }
    }

    class HotelPromosElementVH(itemView: View) : BaseVH<HotelPromosData>(itemView) {
        override fun onBind(data: HotelPromosData) {
            itemView.findViewById<ImageView>(R.id.ivHorizontalListSquareImage)
                .setImageResource(data.imageSrc)
        }
    }

    data class FlightData(
        val imageSrc: Int,
        val place: String,
        val date: String,
        val price: String
    )

    data class HotelData(
        val imageSrc: Int,
    )

    data class HotelPromosData(
        val imageSrc: Int
    )

    enum class ListType {
        FLIGHT,
        HOTEL_PROMOS
    }

}
