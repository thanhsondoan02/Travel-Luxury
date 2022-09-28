package ai.ftech.travelluxury.model.hoteldetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HotelInfo {

    // get from hotel list screen
    var smallestPrice: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var hotelName: String? = null

    @SerializedName("star")
    @Expose
    var star: Float? = null
        get() {
            var value = field ?: 0f
            if (value < 0 || value > 5 || value * 2 - (value * 2).toInt() != 0f) {
                value = 0f
            }
            return value
        }

    @SerializedName("address")
    @Expose
    var address: String? = null
}
