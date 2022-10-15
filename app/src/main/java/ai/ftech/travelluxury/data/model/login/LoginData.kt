package ai.ftech.travelluxury.data.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginData {

    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("msg")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: AccountData? = null

}
