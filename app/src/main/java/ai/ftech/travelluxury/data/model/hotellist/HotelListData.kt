package ai.ftech.travelluxury.data.model.hotellist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HotelListData {
    @SerializedName("code")
    @Expose
    val id: Int? = null

    @SerializedName("msg")
    @Expose
    val msg: String? = null

    @SerializedName("data")
    @Expose
    val data: List<Hotel>? = null
}
