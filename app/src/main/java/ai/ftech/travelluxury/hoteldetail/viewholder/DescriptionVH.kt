package ai.ftech.travelluxury.hoteldetail.viewholder

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.OnClickListener
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.hoteldetail.HotelDetailActivity
import ai.ftech.travelluxury.model.hoteldetail.HotelDetail
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.TextView

class DescriptionVH(itemView: View, listener: OnClickListener) : HotelDetailVH(itemView) {

    private val tvDescriptionShort =
        itemView.findViewById<TextView>(R.id.tvHotelDetailDescription)
    private val tvSeeDescription =
        itemView.findViewById<TextView>(R.id.tvHotelDetailDescriptionSeeDescription)

    init {
        tvSeeDescription.setOnClickListener {
            listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_POLICIES)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bindData() {
        if (HotelDetail.HOTEL_DETAIL.descriptionList == null) return
        if (HotelDetail.HOTEL_DETAIL.descriptionList!!.size != 3) {
            Log.d(TAG, "Description list size is not 3")
            return
        }

        tvDescriptionShort.text = HotelDetail.HOTEL_DETAIL.getDescriptionShort()
    }
}
