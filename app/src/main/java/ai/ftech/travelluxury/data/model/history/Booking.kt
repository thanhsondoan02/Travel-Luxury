package ai.ftech.travelluxury.data.model.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Booking {

    @SerializedName("id")
    @Expose
    var bookingId: Int? = null

    @SerializedName("room_id")
    @Expose
    var roomId: Int? = null

    @SerializedName("check_in")
    @Expose
    var checkIn: String? = null

    @SerializedName("check_out")
    @Expose
    var checkOut: String? = null

    @SerializedName("price")
    @Expose
    var price: Int? = null

    override fun toString(): String {
        return "Booking(bookingId=$bookingId, roomId=$roomId, checkIn=$checkIn, checkOut=$checkOut)"
    }

}
