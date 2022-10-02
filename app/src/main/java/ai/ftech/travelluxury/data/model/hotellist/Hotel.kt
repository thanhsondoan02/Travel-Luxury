package ai.ftech.travelluxury.data.model.hotellist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hotel {

    @SerializedName("id_hotel")
    @Expose
    val id: Int? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    /**
     * star must be in [0.0, 5.0] and step is 0.5
     * else star will be 0.0
     */
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
    val address: String? = null

    @SerializedName("rating_point")
    @Expose
    val ratingPoint: Float? = null

    @SerializedName("rating_count")
    @Expose
    val ratingCount: Int? = null

    @SerializedName("smallest_room_price")
    @Expose
    val smallestRoomPrice: Int? = null

    @SerializedName("image")
    @Expose
    val image: String? = null

    fun isValid(): Boolean {
        return name != null && star != null && address != null && ratingPoint != null && ratingCount != null && smallestRoomPrice != null && image != null
    }
}
