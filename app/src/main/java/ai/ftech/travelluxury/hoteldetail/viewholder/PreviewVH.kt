package ai.ftech.travelluxury.hoteldetail.viewholder

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.loadUrl
import ai.ftech.travelluxury.model.hoteldetail.HotelDetail
import android.util.Log
import android.view.View
import android.widget.ImageView

class PreviewVH(itemView: View) : HotelDetailVH(itemView) {

    private val ivTop = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewTopPicture)
    private val ivBot1 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture1)
    private val ivBot2 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture2)
    private val ivBot3 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture3)
    private val ivBot4 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture4)

    override fun bindData() {
        val imageUrlList = HotelDetail.HOTEL_DETAIL.imageList

        if (imageUrlList == null) {
            Log.d(TAG, "bindData: imageUrlList is null")
            return
        }

        if (HotelDetail.HOTEL_DETAIL.imageList!!.size != 5) {
            Log.d(TAG, "bindData: imageUrlList size is not 5")
            return
        }

        val ivList = listOf(ivTop, ivBot1, ivBot2, ivBot3, ivBot4)
        for (i in imageUrlList.indices) {
            ivList[i].loadUrl(imageUrlList[i])
        }
    }
}

