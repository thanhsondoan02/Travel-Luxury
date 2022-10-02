package ai.ftech.travelluxury.data.model.hoteldetail

import ai.ftech.travelluxury.data.TAG
import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rating {

    @SerializedName("point")
    @Expose
    val point: Float? = null

    @SerializedName("count")
    @Expose
    val count: Int? = null
        get() {
            return if (field == null) {
                Log.d(TAG, "hotel count is null")
                -1
            } else if (field < 0) {
                Log.d(TAG, "invalid hotel count = $field")
                -1
            } else {
                field
            }
        }
}
