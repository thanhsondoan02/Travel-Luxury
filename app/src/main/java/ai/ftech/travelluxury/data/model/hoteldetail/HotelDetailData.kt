package ai.ftech.travelluxury.data.model.hoteldetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HotelDetailData {

    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("msg")
    @Expose
    var msg: String? = null

    @SerializedName("data")
    @Expose
    var data: HotelDetailModel? = null

}
