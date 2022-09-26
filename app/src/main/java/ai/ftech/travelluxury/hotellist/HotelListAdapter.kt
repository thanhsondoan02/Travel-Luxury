package ai.ftech.travelluxury.hotellist

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.getHotelRatingCount
import ai.ftech.travelluxury.data.getPriceString
import ai.ftech.travelluxury.data.loadUrl
import ai.ftech.travelluxury.data.setStar
import ai.ftech.travelluxury.model.hoteldetail.HotelDetail.Companion.HOTEL_DETAIL
import ai.ftech.travelluxury.model.hotellist.Hotel
import ai.ftech.travelluxury.model.hotellist.HotelListModel.Companion.HOTEL_LIST_MODEL
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelListAdapter : RecyclerView.Adapter<HotelListAdapter.HotelVH>() {

    private val hotelList: List<Hotel> = HOTEL_LIST_MODEL.hotelList!!
    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelVH {
        return HotelVH(View.inflate(parent.context, R.layout.hotel_item, null))
    }

    override fun onBindViewHolder(holder: HotelVH, position: Int) {
        holder.bind(hotelList[position])
    }

    override fun getItemCount(): Int {
        return hotelList.size
    }

    inner class HotelVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivHotelImage = itemView.findViewById<ImageView>(R.id.ivHotelImage)
        private val tvName = itemView.findViewById<TextView>(R.id.tvHotelName)
        private val tvAddress = itemView.findViewById<TextView>(R.id.tvHotelAddress)
        private val tvRatingPoint = itemView.findViewById<TextView>(R.id.tvHotelRatingPoint)
        private val tvRatingCount = itemView.findViewById<TextView>(R.id.tvHotelRatingCount)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvHotelPrice)
        private val ivStar1 = itemView.findViewById<ImageView>(R.id.ivHotelStar1)
        private val ivStar2 = itemView.findViewById<ImageView>(R.id.ivHotelStar2)
        private val ivStar3 = itemView.findViewById<ImageView>(R.id.ivHotelStar3)
        private val ivStar4 = itemView.findViewById<ImageView>(R.id.ivHotelStar4)
        private val ivStar5 = itemView.findViewById<ImageView>(R.id.ivHotelStar5)
        private val mcvHotelCard = itemView.findViewById<View>(R.id.mcvHotelCard)

        private val listStarImage = listOf(ivStar1, ivStar2, ivStar3, ivStar4, ivStar5)

        fun bind(hotel: Hotel) {
            ivHotelImage.loadUrl(hotel.image)
            tvName.text = hotel.name
            tvAddress.text = hotel.address
            tvRatingPoint.text = hotel.ratingPoint.toString()
            tvRatingCount.text = getHotelRatingCount(hotel.ratingCount)
            tvPrice.text = getPriceString(hotel.smallestRoomPrice)

            setStar(hotel.star, listStarImage)

            mcvHotelCard.setOnClickListener {
                HOTEL_DETAIL.setHotel(-1, hotel.name, hotel.smallestRoomPrice)
                listener!!.onHotelClick()
            }
        }
    }

    interface Listener {
        fun onHotelClick()
    }

}
