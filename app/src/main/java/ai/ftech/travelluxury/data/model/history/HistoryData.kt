package ai.ftech.travelluxury.data.model.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HistoryData {

    @SerializedName("msg")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Booking>? = null

}
