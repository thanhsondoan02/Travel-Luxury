package ai.ftech.travelluxury.hotellist

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.*
import ai.ftech.travelluxury.model.hoteldetail.HotelDetailModel.Companion.HOTEL_DETAIL_MODEL
import ai.ftech.travelluxury.model.hoteldetail.HotelInfo
import ai.ftech.travelluxury.model.hotellist.Hotel
import ai.ftech.travelluxury.model.hotellist.HotelListModel.Companion.HOTEL_LIST_MODEL
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelListAdapter : RecyclerView.Adapter<HotelListAdapter.HotelVH>(), HotelListContract.View {

    interface Listener {
        fun onHotelClick()
    }

    //    private val hotelList: List<Hotel> = HOTEL_LIST_MODEL.hotelList!!
    var listener: Listener? = null
    private var hotelList: List<Hotel> = mutableListOf()
    private val presenter = HotelListPresenter(this)

    init {
        presenter.getHotelListApi()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelVH {
        return HotelVH(View.inflate(parent.context, R.layout.hotel_item, null))
    }

    override fun onBindViewHolder(holder: HotelVH, position: Int) {
        holder.bind(hotelList[position])
    }

    override fun getItemCount(): Int {
        return hotelList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetHotelList(state: HOTEL_LIST_STATE, message: String) {
        when (state) {
            HOTEL_LIST_STATE.SUCCESS -> {
                hotelList = HOTEL_LIST_MODEL.hotelList!!
                Log.d(TAG, "onGetHotelList: $message")
                notifyDataSetChanged()
            }
            HOTEL_LIST_STATE.NULL_CITY_ID, HOTEL_LIST_STATE.API_ON_RESPONSE_FAILURE, HOTEL_LIST_STATE.API_ON_FAILURE -> {
                Log.d(TAG, "onGetHotelList: $message")
            }
        }
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

            ivHotelImage.loadUrl(hotel.image ?: "")
            tvName.text = hotel.name
            tvAddress.text = hotel.address
            tvRatingPoint.text = hotel.ratingPoint.toString()
            tvRatingCount.text = getHotelRatingCount(hotel.ratingCount ?: 0)
            tvPrice.text = getPriceString(hotel.smallestRoomPrice)

            setStar(hotel.star!!, listStarImage)

            mcvHotelCard.setOnClickListener {
                HOTEL_DETAIL_MODEL.hotelInfo = HotelInfo().apply {
                    id = hotel.id
                    smallestPrice = hotel.smallestRoomPrice
                }
                listener!!.onHotelClick()
            }
        }
    }


}
