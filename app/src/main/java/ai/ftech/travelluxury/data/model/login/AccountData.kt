package ai.ftech.travelluxury.data.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AccountData {

    companion object {
        var INSTANCE: AccountData? = null
    }

    @SerializedName("user_id")
    @Expose
    var id: Int? = null

    @SerializedName("user_name")
    @Expose
    var email: String? = null

    @SerializedName("full_name")
    @Expose
    var fullName: String? = null

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("phone_number")
    var phoneNumber: Int? = null

    @SerializedName("birthday")
    var birthDay: String? = null
}
