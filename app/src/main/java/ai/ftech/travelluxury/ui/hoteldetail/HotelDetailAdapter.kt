package ai.ftech.travelluxury.ui.hoteldetail

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.OnClickListener
import ai.ftech.travelluxury.data.HotelPoliciesHandler
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.loadUrl
import ai.ftech.travelluxury.data.setStar
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailModel
import ai.ftech.travelluxury.data.model.hoteldetail.HotelInfo
import ai.ftech.travelluxury.data.model.hoteldetail.Policy
import ai.ftech.travelluxury.data.model.hoteldetail.Rating
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

class HotelDetailAdapter(private val listener: OnClickListener) : RecyclerView.Adapter<BaseVH>() {

    var imageUrlList: List<String> = listOf()
    var hotelInfo: HotelInfo = HotelInfo()
    var rating = Rating()
    var policyList: List<Policy> = listOf()
    var facilitiesList: List<String> = listOf()

    lateinit var facilitiesAdapter: FacilitiesAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        val inflateView = View.inflate(parent.context, getLayoutResource(viewType), null)

        return when (viewType) {
            0 -> PreviewVH(inflateView)
            1 -> HotelInfoVH(inflateView)
            2 -> RatingVH(inflateView, listener)
            3 -> FacilitiesVH(inflateView, listener)
            4 -> PoliciesVH(inflateView, listener)
            5 -> DescriptionVH(inflateView, listener)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0, 1, 2, 3, 4, 5 -> position
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    private fun getLayoutResource(viewType: Int): Int {
        return when (viewType) {
            0 -> R.layout.hotel_detail_preview_item
            1 -> R.layout.hotel_detail_title_item
            2 -> R.layout.hotel_detail_rating_item
            3 -> R.layout.hotel_detail_facilities_item
            4 -> R.layout.hotel_detail_policies_item
            5 -> R.layout.hotel_detail_description_item
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    inner class PreviewVH(itemView: View) : BaseVH(itemView) {
        private val ivTop = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewTopPicture)
        private val ivBot1 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture1)
        private val ivBot2 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture2)
        private val ivBot3 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture3)
        private val ivBot4 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture4)

        override fun bindData() {
            val ivList = listOf(ivTop, ivBot1, ivBot2, ivBot3, ivBot4)
            var i = 0
            while (i < ivList.size && i < imageUrlList.size) {
                ivList[i].loadUrl(imageUrlList[i])
                i++
            }
        }
    }

    inner class HotelInfoVH(itemView: View) : BaseVH(itemView) {

        private val tvName = itemView.findViewById<TextView>(R.id.tvHotelDetailTitleName)
        private val tvAddress = itemView.findViewById<TextView>(R.id.tvHotelDetailTitleAddress)
        private val ivStar1 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar1)
        private val ivStar2 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar2)
        private val ivStar3 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar3)
        private val ivStar4 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar4)
        private val ivStar5 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar5)

        private val listStarImage = listOf(ivStar1, ivStar2, ivStar3, ivStar4, ivStar5)

        override fun bindData() {
            tvName.text = hotelInfo.hotelName ?: ""
            tvAddress.text = hotelInfo.address ?: ""
            setStar(hotelInfo.star!!, listStarImage)
        }
    }

    inner class RatingVH(itemView: View, listener: OnClickListener) : BaseVH(itemView) {

        private val tvPoint = itemView.findViewById<TextView>(R.id.tvHotelDetailRatingPoint)
        private val tvType = itemView.findViewById<TextView>(R.id.tvHotelDetailRatingType)
        private val tvCount = itemView.findViewById<TextView>(R.id.tvHotelDetailRatingCount)
        private val tvSeeReviews =
            itemView.findViewById<TextView>(R.id.tvHotelDetailRatingSeeReviews)

        init {
            tvSeeReviews.setOnClickListener {
                listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_REVIEWS)
            }
        }

        override fun bindData() {
            tvPoint.text = HotelDetailModel.HOTEL_DETAIL_MODEL.getPointString()
            tvType.text = HotelDetailModel.HOTEL_DETAIL_MODEL.getTypeRating()
            tvCount.text = HotelDetailModel.HOTEL_DETAIL_MODEL.getCountText()
        }
    }

    inner class FacilitiesVH(itemView: View, listener: OnClickListener) : BaseVH(itemView) {

        private val recyclerView =
            itemView.findViewById<RecyclerView>(R.id.rvHotelDetailFacilitiesRecyclerView)
        private val tvSeeFacilities =
            itemView.findViewById<TextView>(R.id.tvHotelDetailFacilitiesSeeFacilities)

        init {
            recyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            facilitiesAdapter = FacilitiesAdapter()
            recyclerView.adapter = facilitiesAdapter

            tvSeeFacilities.setOnClickListener {
                listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_FACILITIES)
            }
        }
    }

    inner class PoliciesVH(itemView: View, listener: OnClickListener) : BaseVH(itemView) {

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

        init {
            tvSeePolicies.setOnClickListener {
                listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_POLICIES)
            }
        }

        override fun bindData() {
            val min = min(2, policyList.size - 1)
            for (i in 0..min) {
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

    inner class DescriptionVH(itemView: View, listener: OnClickListener) : BaseVH(itemView) {

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
            if (HotelDetailModel.HOTEL_DETAIL_MODEL.descriptionList == null) return
            if (HotelDetailModel.HOTEL_DETAIL_MODEL.descriptionList!!.size != 3) {
                Log.d(TAG, "Description list size is not 3")
                return
            }

            tvDescriptionShort.text = HotelDetailModel.HOTEL_DETAIL_MODEL.getDescriptionShort()
        }
    }

}
