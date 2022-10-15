package ai.ftech.travelluxury.ui.main.mybooking

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.dateApiToDateApp
import ai.ftech.travelluxury.data.model.history.Booking
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.BookingVH>() {

    var dataList: List<Booking> = emptyList()
    var view: MyBookingFragment? = null

    private var marginHor: Int? = null
    private var marginVer: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingVH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.history_booking_item, parent, false)
        return BookingVH(view)
    }

    override fun onBindViewHolder(holder: BookingVH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class BookingVH(view: View) : RecyclerView.ViewHolder(view) {

        private val tvBookingID: TextView = view.findViewById(R.id.tvHistoryBookingId)
        private val tvCheckInDate: TextView = view.findViewById(R.id.tvHistoryBookingCheckInDate)
        private val tvCheckOutDate: TextView = view.findViewById(R.id.tvHistoryBookingCheckOutDate)
        private val mcvHistoryCard: MaterialCardView = view.findViewById(R.id.mcvHistoryCard)

        private val params = mcvHistoryCard.layoutParams as RelativeLayout.LayoutParams

        init {
            if (marginHor == null) {
                marginHor = params.leftMargin
                marginVer = params.topMargin
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            if (position == 0 || position == 1) { // x2 margin for first and second item
                params.setMargins(marginHor!!, marginVer!! * 2, marginHor!!, marginVer!!)
            }

            tvBookingID.text = view?.getString(R.string.booking_id) + dataList[position].bookingId
            tvCheckInDate.text = dateApiToDateApp(dataList[position].checkIn!!)
            tvCheckOutDate.text = dateApiToDateApp(dataList[position].checkOut!!)
        }
    }

}
