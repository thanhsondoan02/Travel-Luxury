package ai.ftech.travelluxury.main.home

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseAdapter
import ai.ftech.travelluxury.common.BaseVH
import ai.ftech.travelluxury.data.getFlightList
import ai.ftech.travelluxury.data.getHotelList
import ai.ftech.travelluxury.data.getHotelPromosList
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class HorizontalListAdapter(val type: ListType) : BaseAdapter() {

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getLayoutResource(viewType: Int): Int {
        return when (type) {
            ListType.FLIGHT -> R.layout.horizontal_list_square_bottom_text_item
            ListType.HOTEL -> R.layout.horizontal_list_square_item
            ListType.HOTEL_PROMOS -> R.layout.horizontal_list_rectangle_item
        }
    }

    override fun initData(): MutableList<Any> {
        return when (type) {
            ListType.FLIGHT -> getFlightList()
            ListType.HOTEL -> getHotelList()
            ListType.HOTEL_PROMOS -> getHotelPromosList()
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getViewHolder(inflateView: View, viewType: Int): BaseVH<Any> {
        return when (type) {
            ListType.FLIGHT -> FlightElementVH(inflateView) as BaseVH<Any>
            ListType.HOTEL -> HotelElementVH(inflateView) as BaseVH<Any>
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

    class HotelElementVH(itemView: View) : BaseVH<HotelData>(itemView) {
        override fun onBind(data: HotelData) {
            itemView.findViewById<ImageView>(R.id.ivHorizontalListSquareImage)
                .setImageResource(data.imageSrc)
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
        HOTEL,
        HOTEL_PROMOS
    }

}
