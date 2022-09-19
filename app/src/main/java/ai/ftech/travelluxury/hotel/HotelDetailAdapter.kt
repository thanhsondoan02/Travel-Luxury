package ai.ftech.travelluxury.hotel

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.HotelPoliciesHandler
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.loadUrlToImageView
import ai.ftech.travelluxury.model.hoteldetail.HotelDetail.Companion.HOTEL_DETAIL
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelDetailAdapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<HotelDetailAdapter.HotelDetailVH>() {

    interface OnClickListener {
        fun onClick(nextActivity: HotelDetailActivity.NEXT_ACTIVITY)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelDetailVH {
        return when (viewType) {
            0, 1 -> {
                val inflateView = View.inflate(parent.context, getLayoutResource(viewType), null)
                getViewHolder(inflateView, viewType)
            }
            2 -> {
                val inflateView = View.inflate(parent.context, getLayoutResource(viewType), null)

                val tvSeeReviews =
                    inflateView.findViewById<TextView>(R.id.tvHotelDetailRatingSeeReviews)
                tvSeeReviews.setOnClickListener {
                    listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_REVIEWS)
                }

                getViewHolder(inflateView, viewType)
            }
            3 -> {
                val inflateView = View.inflate(parent.context, getLayoutResource(viewType), null)

                val recyclerView =
                    inflateView.findViewById<RecyclerView>(R.id.rvHotelDetailFacilitiesRecyclerView)
                recyclerView.layoutManager =
                    LinearLayoutManager(inflateView.context, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = FacilitiesAdapter()

                val tvSeeFacilities =
                    inflateView.findViewById<TextView>(R.id.tvHotelDetailFacilitiesSeeFacilities)
                tvSeeFacilities.setOnClickListener {
                    listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_FACILITIES)
                }

                getViewHolder(inflateView, viewType)
            }
            4 -> {
                val inflateView = View.inflate(parent.context, getLayoutResource(viewType), null)

                val tvSeePolicies =
                    inflateView.findViewById<TextView>(R.id.tvHotelDetailPoliciesSeePolicies)
                tvSeePolicies.setOnClickListener {
                    listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_POLICIES)
                }

                getViewHolder(inflateView, viewType)
            }
            5 -> {
                val inflateView = View.inflate(parent.context, getLayoutResource(viewType), null)

                val tvSeeDescription =
                    inflateView.findViewById<TextView>(R.id.tvHotelDetailDescriptionSeeDescription)
                tvSeeDescription.setOnClickListener {
                    listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_POLICIES)
                }

                getViewHolder(inflateView, viewType)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: HotelDetailVH, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 0 // preview
            1 -> 1 // title
            2 -> 2 // rating
            3 -> 3 // facilities
            4 -> 4 // policies
            5 -> 5 // description
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    private fun getViewHolder(inflateView: View, viewType: Int): HotelDetailVH {
        return when (viewType) {
            0 -> PreviewVH(inflateView)
            1 -> TitleVH(inflateView)
            2 -> RatingVH(inflateView)
            3 -> FacilitiesVH(inflateView)
            4 -> PoliciesVH(inflateView)
            5 -> DescriptionVH(inflateView)
            else -> throw IllegalArgumentException("Invalid view type")
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

    open class HotelDetailVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun bindData() {}
    }

    inner class PreviewVH(itemView: View) : HotelDetailVH(itemView) {
        override fun bindData() {
            val imageUrlList = HOTEL_DETAIL.imageList

            if (imageUrlList == null) {
                Log.d(TAG, "bindData: imageUrlList is null")
                return
            }

            if (HOTEL_DETAIL.imageList!!.size != 5) {
                Log.d(TAG, "bindData: imageUrlList size is not 5")
                return
            }

            val ivTop = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewTopPicture)
            val ivBot1 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture1)
            val ivBot2 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture2)
            val ivBot3 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture3)
            val ivBot4 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPreviewBotPicture4)

            val ivList = listOf(ivTop, ivBot1, ivBot2, ivBot3, ivBot4)
            for (i in imageUrlList.indices) {
                val iv = ivList[i]
                loadUrlToImageView(imageUrlList[i], iv, itemView.context)
            }
        }
    }

    class TitleVH(itemView: View) : HotelDetailVH(itemView) {
        override fun bindData() {
            val hotel = HOTEL_DETAIL.hotel

            if (hotel == null) {
                Log.d(TAG, "bindData: hotel is null")
                return
            }

            val tvName = itemView.findViewById<TextView>(R.id.tvHotelDetailTitleName)
            val tvAddress = itemView.findViewById<TextView>(R.id.tvHotelDetailTitleAddress)

            tvName.text = hotel.hotelName
            tvAddress.text = hotel.address
            setStar(hotel.star)
        }

        private fun setStar(star: Float) {
            val ivStar1 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar1)
            val ivStar2 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar2)
            val ivStar3 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar3)
            val ivStar4 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar4)
            val ivStar5 = itemView.findViewById<ImageView>(R.id.ivHotelDetailTitleStar5)

            val listStarImage = listOf(ivStar1, ivStar2, ivStar3, ivStar4, ivStar5)

            for (i in 0 until star.toInt()) {
                listStarImage[i].setImageResource(R.drawable.ic_full_star)
            }

            if (star - star.toInt() > 0) {
                listStarImage[star.toInt()].setImageResource(R.drawable.ic_half_star)
            }
        }
    }

    class RatingVH(itemView: View) : HotelDetailVH(itemView) {
        override fun bindData() {
            val rating = HOTEL_DETAIL.rating

            if (rating == null) {
                Log.d(TAG, "bindData: rating is null")
                return
            }

            val tvPoint = itemView.findViewById<TextView>(R.id.tvHotelDetailRatingPoint)
            val tvType = itemView.findViewById<TextView>(R.id.tvHotelDetailRatingType)
            val tvCount = itemView.findViewById<TextView>(R.id.tvHotelDetailRatingCount)

            tvPoint.text = rating.point.toString()
            tvType.text = HOTEL_DETAIL.getTypeRating()
            tvCount.text = HOTEL_DETAIL.getCountText()
        }
    }

    class FacilitiesVH(itemView: View) : HotelDetailVH(itemView)

    inner class PoliciesVH(itemView: View) : HotelDetailVH(itemView) {

        private val handler = HotelPoliciesHandler()

        override fun bindData() {
            val policyList = HOTEL_DETAIL.policyList

            if (policyList == null) {
                Log.d(TAG, "bindData: policyList is null")
                return
            }

            if (policyList.size != 3) {
                Log.d(TAG, "Policy list size is not 3")
                return
            }

            val tvTitle1 = itemView.findViewById<TextView>(R.id.tvHotelDetailPoliciesTitle1)
            val tvTitle2 = itemView.findViewById<TextView>(R.id.tvHotelDetailPoliciesTitle2)
            val tvTitle3 = itemView.findViewById<TextView>(R.id.tvHotelDetailPoliciesTitle3)
            val ivIcon1 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPoliciesIcon1)
            val ivIcon2 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPoliciesIcon2)
            val ivIcon3 = itemView.findViewById<ImageView>(R.id.ivHotelDetailPoliciesIcon3)
            val tvDescription1 =
                itemView.findViewById<TextView>(R.id.ivHotelDetailPoliciesDescription1)
            val tvDescription2 =
                itemView.findViewById<TextView>(R.id.ivHotelDetailPoliciesDescription2)
            val tvDescription3 =
                itemView.findViewById<TextView>(R.id.ivHotelDetailPoliciesDescription3)

            val tvTitleList = listOf(tvTitle1, tvTitle2, tvTitle3)
            val tvDesList = listOf(tvDescription1, tvDescription2, tvDescription3)
            val ivIconList = listOf(ivIcon1, ivIcon2, ivIcon3)

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

    class DescriptionVH(itemView: View) : HotelDetailVH(itemView) {
        @SuppressLint("SetTextI18n")
        override fun bindData() {
            if (HOTEL_DETAIL.descriptionList == null) return
            if (HOTEL_DETAIL.descriptionList!!.size != 3) {
                Log.d(TAG, "Description list size is not 3")
                return
            }

            val tvDescriptionShort = itemView.findViewById<TextView>(R.id.tvHotelDetailDescription)

            tvDescriptionShort.text = HOTEL_DETAIL.getDescriptionShort()
        }
    }


}
