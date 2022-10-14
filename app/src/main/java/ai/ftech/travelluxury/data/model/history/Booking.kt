package ai.ftech.travelluxury.data.model.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Booking {

    @SerializedName("booking_id")
    @Expose
    var bookingId: Int? = null

    @SerializedName("room_id")
    @Expose
    var roomId: Int? = null

    @SerializedName("name_hotel")
    @Expose
    var hotelName: String? = null

    @SerializedName("check_in")
    @Expose
    var checkIn: String? = null

    @SerializedName("check_out")
    @Expose
    var checkOut: String? = null

    @SerializedName("price_room")
    @Expose
    var price: Int? = null

    @SerializedName("msg_status")
    @Expose
    var status: String? = null

    override fun toString(): String {
        return "Booking(bookingId=$bookingId, roomId=$roomId, checkIn=$checkIn, checkOut=$checkOut)"
    }

}
