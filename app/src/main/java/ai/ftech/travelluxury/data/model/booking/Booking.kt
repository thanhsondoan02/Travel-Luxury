package ai.ftech.travelluxury.data.model.booking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Booking {

    @SerializedName("booking_id")
    @Expose
    var bookingId: Int? = null

    @SerializedName("id_room")
    @Expose
    var roomId: Int? = null

    @SerializedName("check_in")
    @Expose
    var checkIn: String? = null

    @SerializedName("check_out")
    @Expose
    var checkOut: String? = null

    override fun toString(): String {
        return "Booking(bookingId=$bookingId, roomId=$roomId, checkIn=$checkIn, checkOut=$checkOut)"
    }

}
