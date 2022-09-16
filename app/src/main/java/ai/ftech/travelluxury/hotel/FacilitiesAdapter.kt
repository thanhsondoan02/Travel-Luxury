package ai.ftech.travelluxury.hotel

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.model.HotelDetail.Companion.HOTEL_DETAIL
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FacilitiesAdapter : RecyclerView.Adapter<FacilitiesAdapter.FacilityVH>() {

    private val dataList by lazy {
        HOTEL_DETAIL.getFacilitiesList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityVH {
        val view = View.inflate(parent.context, R.layout.hotel_detail_facilities_icon_item, null)
        return FacilityVH(view)
    }

    override fun onBindViewHolder(holder: FacilityVH, position: Int) {
        val ivIcon = holder.itemView.findViewById<ImageView>(R.id.ivHotelDetailFacilitiesIcon)
        val tvTitle = holder.itemView.findViewById<TextView>(R.id.tvHotelDetailFacilitiesName)

        ivIcon.setImageResource(dataList[position].iconID)
        tvTitle.text = dataList[position].name
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class FacilityVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    data class FacilityData(
        val iconID: Int,
        val name: String
    )
}
