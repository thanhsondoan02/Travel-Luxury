package ai.ftech.travelluxury.data.model.selectroom

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchRoomBody {

    @SerializedName("idHotel")
    @Expose
    var idHotel: Int? = null

    @SerializedName("checkin")
    @Expose
    var checkInDate: String? = null

    @SerializedName("checkout")
    @Expose
    var checkOutDate: String? = null

    @SerializedName("page")
    @Expose
    var page: Int? = null

    override fun toString(): String {
        return "SearchRoomBody(idHotel=$idHotel, checkInDate=$checkInDate, checkOutDate=$checkOutDate, page=$page)"
    }

}
