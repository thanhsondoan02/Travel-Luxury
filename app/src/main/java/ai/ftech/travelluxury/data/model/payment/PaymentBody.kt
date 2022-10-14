package ai.ftech.travelluxury.data.model.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentBody {

    @SerializedName("id")
    @Expose
    var bookingId: Int? = null

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

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
        return "PaymentBody(bookingId=$bookingId, userId=$userId, roomId=$roomId, checkIn=$checkIn, checkOut=$checkOut, price=$price)"
    }

}
