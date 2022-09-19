package ai.ftech.travelluxury.hoteldetail

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.HotelFacilitiesHandler
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.model.hoteldetail.HotelDetail.Companion.HOTEL_DETAIL
import android.util.Log
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
        if (dataList == null) {
            Log.d(TAG, "onBindViewHolder: dataList is null")
            return
        }

        if (!handler.isValidType(dataList[position])) {
            Log.d(TAG, "onBindViewHolder: invalid facility type ${dataList[position]}")
            return
        }

        val type = handler.getType(dataList[position])
        holder.ivIcon.setImageResource(handler.getIcon(type))
        holder.tvTitle.text = handler.getTitle(type)
    }

    override fun getItemCount(): Int {
        if (dataList == null) return 0
        return dataList.size
    }

    class FacilityVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.ivHotelDetailFacilitiesIcon)
        val tvTitle: TextView = itemView.findViewById(R.id.tvHotelDetailFacilitiesName)
    }
}
