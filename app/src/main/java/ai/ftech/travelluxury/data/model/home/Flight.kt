package ai.ftech.travelluxury.data.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("id_flight")
    @Expose
    val id: Int,
    @SerializedName("image_flight")
    @Expose
    val image: String,
    @SerializedName("title_flight")
    @Expose
    val title: String,
    @SerializedName("date_flight")
    @Expose
    val date: String
)
