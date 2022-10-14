package ai.ftech.travelluxury.data.model.selectroom

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Room {

    @SerializedName("id_room")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("current_price")
    @Expose
    var price: Float? = null

    @SerializedName("max_guest")
    @Expose
    var guessNumber: Int? = null

    @SerializedName("bed_type")
    @Expose
    var bedType: String? = null

    @SerializedName("break_fast")
    @Expose
    var breakfast: String? = null

    @SerializedName("refundable")
    @Expose
    var refund: String? = null

    var imageList: List<String>? = null

    override fun toString(): String {
        return "Room(id=$id, name=$name, price=$price, guessNumber=$guessNumber, bedType=$bedType, breakfast=$breakfast, refund=$refund, imageList=$imageList)"
    }
}
