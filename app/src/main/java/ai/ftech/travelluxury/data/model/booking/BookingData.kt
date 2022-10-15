package ai.ftech.travelluxury.data.model.booking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookingData {

    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("msg")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Booking>? = null
}
