package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.model.selectroom.Room
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel.Companion.INSTANCE
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView

class SelectRoomAdapter : RecyclerView.Adapter<RoomVH>() {

    interface IListener {
        fun onRoomSelected(room: Room)
    }

    var listener: IListener? = null

    private val roomList = INSTANCE.roomList
    private var isFirstItem = true
    private var marginHor: Int = 0
    private var marginVer: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomVH {
        val inflateView = View.inflate(parent.context, R.layout.room_item, null)

        // fix first item margin top
        val mcvRoomCard = inflateView.findViewById<View>(R.id.mcvRoomCard)
        val params = mcvRoomCard.layoutParams as RelativeLayout.LayoutParams
        if (isFirstItem) {
            isFirstItem = false
            marginHor = params.leftMargin
            marginVer = params.topMargin
            params.setMargins(marginHor, marginVer * 2, marginHor, marginVer)
        } else {
            params.setMargins(marginHor, marginVer, marginHor, marginVer)
        }
        mcvRoomCard.layoutParams = params

        return RoomVH(inflateView).apply {
            listener = object : RoomVH.IListener {
                override fun onRoomSelected(room: Room) {
                    this@SelectRoomAdapter.listener?.onRoomSelected(room)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RoomVH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        if (roomList == null) {
            Log.d(TAG, "getItemCount: roomList is null")
            return 0
        }
        return roomList.size
    }
}
