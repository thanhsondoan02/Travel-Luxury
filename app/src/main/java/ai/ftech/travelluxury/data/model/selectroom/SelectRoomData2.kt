package ai.ftech.travelluxury.data.model.selectroom

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SelectRoomData2 {

    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("msg")
    @Expose
    var msg: String? = null

    @SerializedName("data")
    @Expose
    var data: TempData? = null

}

class TempData {

    @SerializedName("list_room")
    @Expose
    var listRoom: List<RoomAndImageList>? = null
}
