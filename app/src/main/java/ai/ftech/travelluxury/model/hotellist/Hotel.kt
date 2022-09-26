package ai.ftech.travelluxury.model.hotellist

import ai.ftech.travelluxury.data.TAG
import android.util.Log

class Hotel(
    val name: String,
    star: Float,
    val address: String,
    val ratingPoint: Float,
    val ratingCount: Int,
    val smallestRoomPrice: Int,
    val image: String
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
