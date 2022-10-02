package ai.ftech.travelluxury.data.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("code")
    @Expose
    val code: Int?,
    @SerializedName("msg")
    @Expose
    val msg: String?,
    @SerializedName("data")
    @Expose
    val data: Data?
)
