package ai.ftech.travelluxury.hotel

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.HotelFacilitiesHandler
import ai.ftech.travelluxury.model.hoteldetail.HotelDetail.Companion.HOTEL_DETAIL
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FacilitiesAdapter : RecyclerView.Adapter<FacilitiesAdapter.FacilityVH>() {

    private val dataList = HOTEL_DETAIL.facilitiesList
    private val handler = HotelFacilitiesHandler()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityVH {
        val view = View.inflate(parent.context, R.layout.hotel_detail_facilities_icon_item, null)
        return FacilityVH(view)
    }

    override fun onBindViewHolder(holder: FacilityVH, position: Int) {
        if (dataList == null) return

        val ivIcon = holder.itemView.findViewById<ImageView>(R.id.ivHotelDetailFacilitiesIcon)
        val tvTitle = holder.itemView.findViewById<TextView>(R.id.tvHotelDetailFacilitiesName)

        if (handler.isValidType(dataList[position])) {
            val type = handler.getType(dataList[position])
            ivIcon.setImageResource(handler.getIcon(type))
            tvTitle.text = handler.getTitle(type)
        }
    }

    override fun getItemCount(): Int {
        if (dataList == null) return 0
        return dataList.size
    }

    class FacilityVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    data class FacilityData(
        val iconID: Int,
        val name: String
    )
}
