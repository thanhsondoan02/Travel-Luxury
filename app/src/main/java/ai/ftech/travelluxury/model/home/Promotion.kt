package ai.ftech.travelluxury.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Promotion(
    @SerializedName("id_promotion")
    @Expose
    val id: Int,
    @Expose
    @SerializedName("image_promotion") val image: String
)
