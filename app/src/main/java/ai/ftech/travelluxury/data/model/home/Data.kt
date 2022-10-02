package ai.ftech.travelluxury.data.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("list_flight")
    @Expose
    val listFlight: List<Flight>,
    @SerializedName("list_promotion")
    @Expose
    val listPromotion: List<Promotion>,
    @SerializedName("list_city")
    @Expose
    val listCity: List<City>
)
