package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.getPriceString
import ai.ftech.travelluxury.data.model.selectroom.Room
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class RoomVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var activity: SelectRoomActivity? = null
    var listener: SelectRoomActivity.IListener? = null

    private val tvName = itemView.findViewById<TextView>(R.id.tvRoomName)
    private val tvSeeDetail = itemView.findViewById<TextView>(R.id.tvRoomSeeDetails)
    private val tvGuest = itemView.findViewById<TextView>(R.id.tvRoomGuest)
    private val tvBed = itemView.findViewById<TextView>(R.id.tvRoomBed)
    private val tvBreakfast = itemView.findViewById<TextView>(R.id.tvRoomBreakfast)
    private val tvRefund = itemView.findViewById<TextView>(R.id.tvRoomRefund)
    private val tvPrice = itemView.findViewById<TextView>(R.id.tvRoomPrice)
    private val btnSelect = itemView.findViewById<Button>(R.id.btnRoomSelect)
    private val vpImages = itemView.findViewById<ViewPager>(R.id.vpRoomImages)
    private val diDotsIndicator = itemView.findViewById<DotsIndicator>(R.id.diRoomDotsIndicator)

    private var roomSelected: Room? = null

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

    fun bind(room: Room) {
        roomSelected = room

        tvName.text = room.name
        tvGuest.text = SelectRoomModel.INSTANCE.getGuessString(room.guessNumber ?: -1)
        tvBed.text = room.bedType
        tvBreakfast.text = room.breakfast
        tvRefund.text = room.refund
        tvPrice.text = getPriceString(room.price)

        vpImages.adapter = RoomImageAdapter().apply {
            imageList = room.imageList ?: emptyList()
            listener = object : RoomImageAdapter.IListener {
                override fun onImageClicked() {
                    this@RoomVH.listener?.onImageClick(
                        room.imageList ?: emptyList(),
                        vpImages.currentItem
                    )
                }
            }
        }

        diDotsIndicator.attachTo(vpImages)

        btnSelect.setBackgroundColor(
            if (isButtonValid())
                activity!!.getColor(R.color.button_valid)
            else
                activity!!.getColor(R.color.gray)
        )
    }

    private fun isButtonValid(): Boolean {
        return SelectRoomModel.INSTANCE.duration != null && SelectRoomModel.INSTANCE.checkInDate != null
    }

}
