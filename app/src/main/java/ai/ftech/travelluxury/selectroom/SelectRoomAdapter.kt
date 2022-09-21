package ai.ftech.travelluxury.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.model.selectroom.SelectRoomModel.Companion.SELECT_ROOM_MODEL
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SelectRoomAdapter : RecyclerView.Adapter<RoomVH>() {

    private val roomList = SELECT_ROOM_MODEL.roomList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomVH {
        val inflateView = View.inflate(parent.context, R.layout.room_item, null)
        return RoomVH(inflateView)
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
