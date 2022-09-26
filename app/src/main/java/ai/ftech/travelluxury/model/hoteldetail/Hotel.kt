package ai.ftech.travelluxury.model.hoteldetail

import ai.ftech.travelluxury.data.TAG
import android.util.Log

class Hotel(
    val id: Int,
    val hotelName: String,
    val smallestPrice: Int,
) {
    var star: Float? = null
        set(value) {
            field = if (value!! < 0 || value > 5 || value * 2 - (value * 2).toInt() != 0f) {
                Log.d(TAG, "star = $star")
                0f
            } else {
                value
            }
        }
    var address: String? = null
}
