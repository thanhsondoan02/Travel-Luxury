package ai.ftech.travelluxury.hoteldetail.viewholder

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.OnClickListener
import ai.ftech.travelluxury.data.HotelPoliciesHandler
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.hoteldetail.HotelDetailActivity
import ai.ftech.travelluxury.model.hoteldetail.HotelDetail
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class PoliciesVH(itemView: View, listener: OnClickListener) : HotelDetailVH(itemView) {

    private val tvTitle1 = itemView.findViewById<TextView>(R.id.tvHotelDetailPoliciesTitle1)
    private val tvTitle2 = itemView.findViewById<TextView>(R.id.tvHotelDetailPoliciesTitle2)
    private val tvTitle3 = itemView.findViewById<TextView>(R.id.tvHotelDetailPoliciesTitle3)
    private val ivIcon1 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPoliciesIcon1)
    private val ivIcon2 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPoliciesIcon2)
    private val ivIcon3 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPoliciesIcon3)
    private val tvDescription1 =
        itemView.findViewById<TextView>(R.id.ivHotelDetailPoliciesDescription1)
    private val tvDescription2 =
        itemView.findViewById<TextView>(R.id.ivHotelDetailPoliciesDescription2)
    private val tvDescription3 =
        itemView.findViewById<TextView>(R.id.ivHotelDetailPoliciesDescription3)
    private val tvSeePolicies =
        itemView.findViewById<TextView>(R.id.tvHotelDetailPoliciesSeePolicies)

    private val handler = HotelPoliciesHandler()
    private val tvTitleList = listOf(tvTitle1, tvTitle2, tvTitle3)
    private val tvDesList = listOf(tvDescription1, tvDescription2, tvDescription3)
    private val ivIconList = listOf(ivIcon1, ivIcon2, ivIcon3)
    private val policyList = HotelDetail.HOTEL_DETAIL.policyList

    init {
        tvSeePolicies.setOnClickListener {
            listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_POLICIES)
        }
    }

    override fun bindData() {
        if (policyList == null) {
            Log.d(TAG, "bindData: policyList is null")
            return
        }

        if (policyList.size != 3) {
            Log.d(TAG, "Policy list size is not 3")
            return
        }

        for (i in policyList.indices) {
            val typeInString = policyList[i].type

            if (handler.isValidType(typeInString)) {
                val policyType = handler.getType(typeInString)
                ivIconList[i].setImageResource(handler.getIcon(policyType))
                tvTitleList[i].text = handler.getTitle(policyType)
                tvDesList[i].text = policyList[i].description
            }
        }
    }
}
