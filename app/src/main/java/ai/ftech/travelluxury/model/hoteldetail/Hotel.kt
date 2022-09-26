package ai.ftech.travelluxury.model.hoteldetail

import ai.ftech.travelluxury.data.TAG
import android.util.Log

class Hotel(
    val id: Int,
    val hotelName: String,
    val smallestPrice: Int,
    star: Float,
    val address: String
) {
    val star: Float

    init {
        val value = star
        this.star = if (value < 0 || value > 5 || value * 2 - (value * 2).toInt() != 0f) {
            Log.d(TAG, "star = $star")
            0f
        } else {
            value
        }
    }

}
