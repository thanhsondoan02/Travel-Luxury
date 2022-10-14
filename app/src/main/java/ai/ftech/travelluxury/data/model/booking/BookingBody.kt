package ai.ftech.travelluxury.data.model.booking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookingBody {

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("room_id")
    @Expose
    var roomId: Int? = null

    @SerializedName("checkin")
    @Expose
    var checkIn: String? = null

    @SerializedName("checkout")
    @Expose
    var checkOut: String? = null

    override fun toString(): String {
        return "BookingBody(userId=$userId, roomId=$roomId, checkIn=$checkIn, checkOut=$checkOut)"
    }

}
