package ai.ftech.travelluxury.data.model.selectroom

import com.google.gson.annotations.SerializedName

class RoomAndImageList {

    @SerializedName("room")
    var room: Room? = null

    @SerializedName("list_image")
    var imageList: List<String>? = null

}
