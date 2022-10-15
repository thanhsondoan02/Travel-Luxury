package ai.ftech.travelluxury.ui.hotellist

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.getHotelRatingCount
import ai.ftech.travelluxury.data.getPriceString
import ai.ftech.travelluxury.data.loadUrl
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailModel.Companion.INSTANCE
import ai.ftech.travelluxury.data.model.hoteldetail.HotelInfo
import ai.ftech.travelluxury.data.model.hotellist.Hotel
import ai.ftech.travelluxury.data.setStar
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelListAdapter : RecyclerView.Adapter<HotelListAdapter.HotelVH>() {

    interface Listener {
        fun onHotelClick()
    }

    var listener: Listener? = null
    var hotelList: List<Hotel> = mutableListOf()

    private var isFirstItem = true
    private var marginHor: Int = 0
    private var marginVer: Int = 0

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

        init {
            // fix first item margin top
            val params = mcvHotelCard.layoutParams as RelativeLayout.LayoutParams
            if (isFirstItem) {
                isFirstItem = false
                marginHor = params.leftMargin
                marginVer = params.topMargin
                params.setMargins(marginHor, marginVer * 2, marginHor, marginVer)
            } else {
                params.setMargins(marginHor, marginVer, marginHor, marginVer)
            }
            mcvHotelCard.layoutParams = params
        }

        fun bind(hotel: Hotel) {

            ivHotelImage.loadUrl(hotel.image ?: "")
            tvName.text = hotel.name
            tvAddress.text = hotel.address
            tvRatingPoint.text = hotel.ratingPoint.toString()
            tvRatingCount.text = getHotelRatingCount(hotel.ratingCount ?: 0)
            tvPrice.text = getPriceString(hotel.smallestRoomPrice)

            setStar(hotel.star!!, listStarImage)

            mcvHotelCard.setOnClickListener {
                INSTANCE.hotelInfo = HotelInfo().apply {
                    id = hotel.id
                    smallestPrice = hotel.smallestRoomPrice
                }
                listener!!.onHotelClick()
            }
        }
    }


}
