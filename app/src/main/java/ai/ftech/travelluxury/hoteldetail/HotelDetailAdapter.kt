package ai.ftech.travelluxury.hoteldetail

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.OnClickListener
import ai.ftech.travelluxury.hoteldetail.viewholder.*
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HotelDetailAdapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<HotelDetailVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelDetailVH {
        val inflateView = View.inflate(parent.context, getLayoutResource(viewType), null)

        return when (viewType) {
            0 -> PreviewVH(inflateView)
            1 -> TitleVH(inflateView)
            2 -> RatingVH(inflateView, listener)
            3 -> FacilitiesVH(inflateView, listener)
            4 -> PoliciesVH(inflateView, listener)
            5 -> DescriptionVH(inflateView, listener)
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
}
