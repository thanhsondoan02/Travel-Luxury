package ai.ftech.travelluxury.hoteldetail.viewholder

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.setStar
import ai.ftech.travelluxury.model.hoteldetail.HotelDetail
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class TitleVH(itemView: View) : HotelDetailVH(itemView) {

    private val tvName = itemView.findViewById<TextView>(R.id.tvHotelDetailTitleName)
    private val tvAddress = itemView.findViewById<TextView>(R.id.tvHotelDetailTitleAddress)
    private val ivStar1 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar1)
    private val ivStar2 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar2)
    private val ivStar3 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar3)
    private val ivStar4 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar4)
    private val ivStar5 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar5)

    private val listStarImage = listOf(ivStar1, ivStar2, ivStar3, ivStar4, ivStar5)
    private val hotel = HotelDetail.HOTEL_DETAIL.hotel

    override fun bindData() {
        if (hotel == null) {
            Log.d(TAG, "bindData: hotel is null")
            return
        }

        tvName.text = hotel.hotelName
        tvAddress.text = hotel.address
        setStar(hotel.star!!, listStarImage)
    }


}

