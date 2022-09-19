package ai.ftech.travelluxury.model.hoteldetail

import ai.ftech.travelluxury.data.TAG
import android.util.Log

class Rating(point: Float, val count: Int) {

    val point: Float

    init {
        if (point < 0 || point > 10) {
            Log.d(TAG, "hotel point = $point")
            this.point = 0f
        } else {
            this.point = point
        }
    }
}
