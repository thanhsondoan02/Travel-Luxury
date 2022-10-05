package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.getPriceString
import ai.ftech.travelluxury.data.model.selectroom.Room
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel.Companion.SELECT_ROOM_MODEL
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

class RoomVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    interface IListener {
        fun onRoomSelected(room: Room)
    }

    var listener: IListener? = null

    private val tvName = itemView.findViewById<TextView>(R.id.tvRoomName)
    private val tvSeeDetail = itemView.findViewById<TextView>(R.id.tvRoomSeeDetails)
    private val tvGuest = itemView.findViewById<TextView>(R.id.tvRoomGuest)
    private val tvBed = itemView.findViewById<TextView>(R.id.tvRoomBed)
    private val tvBreakfast = itemView.findViewById<TextView>(R.id.tvRoomBreakfast)
    private val tvRefund = itemView.findViewById<TextView>(R.id.tvRoomRefund)
    private val tvPrice = itemView.findViewById<TextView>(R.id.tvRoomPrice)
    private val btnSelect = itemView.findViewById<TextView>(R.id.btnRoomSelect)
    private val vpImages = itemView.findViewById<ViewPager>(R.id.vpRoomImages)

    private var roomSelected: Room? = null
    private val roomList = SELECT_ROOM_MODEL.roomList

    init {
        vpImages.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageSelected(position: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

        })

        btnSelect.setOnClickListener {
            if (listener != null && roomSelected != null) {
                listener!!.onRoomSelected(roomSelected!!)
            }
        }
    }

    fun bind(index: Int) {
        if (roomList != null && roomList.size > index) {
            val room = roomList[index]

            roomSelected = room

            tvName.text = room.name
            tvGuest.text = SELECT_ROOM_MODEL.getGuessString(room.guessNumber)
            tvBed.text = room.bedType
            tvBreakfast.text = room.breakfast
            tvRefund.text = room.refund
            tvPrice.text = getPriceString(room.price)

            vpImages.adapter = RoomImageAdapter(room.imageList)
        }
    }

}
