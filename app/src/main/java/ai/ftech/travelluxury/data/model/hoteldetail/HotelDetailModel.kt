package ai.ftech.travelluxury.data.model.hoteldetail

import ai.ftech.travelluxury.data.TAG
import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HotelDetailModel {

    companion object {
        val INSTANCE = HotelDetailModel()
    }

    @SerializedName("list_image")
    @Expose
    var imageList: List<String>? = null

    @SerializedName("hotel_info")
    @Expose
    var hotelInfo: HotelInfo? = null

    @SerializedName("rating")
    @Expose
    var rating: Rating? = null

    @SerializedName("list_facilities")
    @Expose
    var facilitiesList: List<String>? = null

    @SerializedName("list_policies")
    @Expose
    var policyList: List<Policy>? = null

    @SerializedName("list_description")
    @Expose
    var descriptionList: List<String>? = null

//    fun mockData() {
//
//        val imageTop =
//            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
//        val imageBot1 =
//            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-8edf3caa310bfe8644baf32ac8a95e46.webp?alt=media&token=2609510c-1f74-4e2d-9d89-a58e3d610cd6"
//        val imageBot2 =
//            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-96fdc30e525f75f478d6cf9977234d70.webp?alt=media&token=64122494-b048-4356-b67e-b494d006c1bf"
//        val imageBot3 =
//            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-c6d6c4477981d833c2732b2ef13b01f8.webp?alt=media&token=ee9688eb-4808-4540-ace6-745e8b993738"
//        val imageBot4 =
//            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
//
//        imageList = listOf(imageTop, imageBot1, imageBot2, imageBot3, imageBot4)
//
//        val point = 9.2f
//        val count = 580
//        rating = Rating(point, count)
//
//        facilitiesList = listOf(
//            "ac",
//            "restaurant",
//            "hour_24",
//            "parking",
//            "elevator",
//            "wifi"
//        )
//
//        val policyType1 = "check_in_out"
//        val policyType2 = "additional"
//        val policyType3 = "general"
//        val policiesDescription1 = "Check-in time from 14:00\nCheck-out time before 12:00"
//        val policiesDescription2 =
//            "Located in the center of Hanoi Old Quarter, newly renovated in 2018, Classy Boutique Hotel is a 5-minute walk from Hoan Kiem Lake and Ngoc Son Temple. It offers free WiFi access and free parking."
//        val policiesDescription3 =
//            "Please note that Your Children Might Be Charged when check-in at the hotel. Please call the hotel before your check-in date for further information. Vui long luu y, tre em"
//
//        policyList = listOf(
//            Policies(policyType1, policiesDescription1),
//            Policies(policyType2, policiesDescription2),
//            Policies(policyType3, policiesDescription3)
//        )
//
//        val descriptionShort1 =
//            "Hanoi Chic Boutique Hotel is a hotel in a good neighborhood which is located at Hang Bo Ward."
//        val descriptionShort2 =
//            "24-hours front desk is available to serve you, from check-in to check-out, or any assistance you need. Should you desire more, do not hesitate to ask the front desk, we are always ready to accommodate you."
//        val descriptionShort3 =
//            "Wifi is available within public areas of the public area of the property to help you to stay connected with family and friends."
//        descriptionList = listOf(descriptionShort1, descriptionShort2, descriptionShort3)
//    }

    fun getDescriptionShort(): String {
        if (descriptionList == null) return ""
        if (descriptionList!!.size != 3) {
            Log.d(TAG, "description list size is not 3")
            return ""
        }

        return "${descriptionList!![0]}\n\n${descriptionList!![1]}\n\n${descriptionList!![2]}"
    }

    fun getTypeRating(): String {
        return if (rating == null) {
            Log.d(TAG, "getTypeRating: rating is null")
            ""
        } else if (rating!!.point == null) {
            Log.d(TAG, "getTypeRating: rating point is null")
            ""
        } else {
            when (rating!!.point!!) {
                in 8.0..10.0 -> "Superb"
                in 6.0..8.0 -> "Very Good"
                in 4.0..6.0 -> "Good"
                in 0.0..4.0 -> "Poor"
                else -> {
                    Log.d(TAG, "getTypeRating: rating point is not in range")
                    ""
                }
            }
        }
    }

    fun getCountText(): String {
        return if (rating == null) {
            Log.d(TAG, "getCountText: rating is null")
            ""
        } else if (rating!!.count == -1) {
            ""
        } else {
            "From ${rating!!.count} reviews"
        }
    }

    fun getPointString(): String {
        return if (rating == null) {
            Log.d(TAG, "getPointString: rating is null")
            ""
        } else if (rating!!.point == null) {
            Log.d(TAG, "getPointString: rating point is null")
            ""
        } else if (rating!!.point!! < 0 || rating!!.point!! > 10) {
            Log.d(TAG, "getPointString: rating point is not in range")
            ""
        } else {
            rating!!.point!!.toString()
        }
    }

}
