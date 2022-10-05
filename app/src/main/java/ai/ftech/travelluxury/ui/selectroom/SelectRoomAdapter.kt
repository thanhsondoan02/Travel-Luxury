package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.model.selectroom.Room
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel.Companion.SELECT_ROOM_MODEL
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SelectRoomAdapter : RecyclerView.Adapter<RoomVH>() {

    interface IListener {
        fun onRoomSelected(room: Room)
    }

    var listener: IListener? = null

    private val roomList = SELECT_ROOM_MODEL.roomList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomVH {
        val inflateView = View.inflate(parent.context, R.layout.room_item, null)
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
