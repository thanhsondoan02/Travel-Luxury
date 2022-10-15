package ai.ftech.travelluxury.data.model.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentData {

    @SerializedName("msg")
    @Expose
    var message: String? = null

}
