package ai.ftech.travelluxury.hoteldetail.viewholder

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.OnClickListener
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.hoteldetail.HotelDetailActivity
import ai.ftech.travelluxury.model.hoteldetail.HotelDetail
import android.util.Log
import android.view.View
import android.widget.TextView

class RatingVH(itemView: View, listener: OnClickListener) : HotelDetailVH(itemView) {

    private val tvPoint = itemView.findViewById<TextView>(R.id.tvHotelDetailRatingPoint)
    private val tvType = itemView.findViewById<TextView>(R.id.tvHotelDetailRatingType)
    private val tvCount = itemView.findViewById<TextView>(R.id.tvHotelDetailRatingCount)
    private val tvSeeReviews =
        itemView.findViewById<TextView>(R.id.tvHotelDetailRatingSeeReviews)

    private val rating = HotelDetail.HOTEL_DETAIL.rating

    init {
        tvSeeReviews.setOnClickListener {
            listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_REVIEWS)
        }
    }

    override fun bindData() {
        if (rating == null) {
            Log.d(TAG, "bindData: rating is null")
            return
        }

        tvPoint.text = rating.point.toString()
        tvType.text = HotelDetail.HOTEL_DETAIL.getTypeRating()
        tvCount.text = HotelDetail.HOTEL_DETAIL.getCountText()
    }
}
