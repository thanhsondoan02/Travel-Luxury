package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.model.selectroom.Room
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView

class SelectRoomAdapter : RecyclerView.Adapter<RoomVH>() {

    var listener: SelectRoomActivity.IListener? = null
    var roomList: List<Room> = emptyList()
    var activity: SelectRoomActivity? = null

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
            listener = this@SelectRoomAdapter.listener
            activity = this@SelectRoomAdapter.activity
        }
    }

    override fun onBindViewHolder(holder: RoomVH, position: Int) {
        val room = roomList[position]
        holder.bind(room)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}
