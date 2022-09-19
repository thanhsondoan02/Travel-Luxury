package ai.ftech.travelluxury.model.hoteldetail

import ai.ftech.travelluxury.data.TAG
import android.util.Log

class Hotel(
    val hotelName: String,
    star: Float,
    val address: String
) {

    val star: Float

    init {
        if (star < 0 || star > 5 || star * 2 - (star * 2).toInt() != 0f) {
            Log.d(TAG, "star = $star")
            this.star = 0f
        } else {
            this.star = star
        }
    }
}
