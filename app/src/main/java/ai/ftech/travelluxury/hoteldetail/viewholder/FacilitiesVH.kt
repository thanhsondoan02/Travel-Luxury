package ai.ftech.travelluxury.hoteldetail.viewholder

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.OnClickListener
import ai.ftech.travelluxury.hoteldetail.FacilitiesAdapter
import ai.ftech.travelluxury.hoteldetail.HotelDetailActivity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FacilitiesVH(itemView: View, listener: OnClickListener) : HotelDetailVH(itemView) {

    private val recyclerView =
        itemView.findViewById<RecyclerView>(R.id.rvHotelDetailFacilitiesRecyclerView)
    private val tvSeeFacilities =
        itemView.findViewById<TextView>(R.id.tvHotelDetailFacilitiesSeeFacilities)

    init {
        recyclerView.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = FacilitiesAdapter()

        tvSeeFacilities.setOnClickListener {
            listener.onClick(HotelDetailActivity.NEXT_ACTIVITY.SEE_FACILITIES)
        }
    }
}
