package ai.ftech.travelluxury.model.hoteldetail

import ai.ftech.travelluxury.data.TAG
import android.util.Log

class HotelDetail {

    companion object {
        val HOTEL_DETAIL = HotelDetail()
    }

    var imageList: List<String>? = null         // 5 images
    var hotel: Hotel? = null
    var rating: Rating? = null
    var facilitiesList: List<String>? = null
    var policyList: List<Policies>? = null      // 3 policies
    var descriptionList: List<String>? = null   // 3 descriptions

    fun mockData() {

        val imageTop =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        val imageBot1 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-8edf3caa310bfe8644baf32ac8a95e46.webp?alt=media&token=2609510c-1f74-4e2d-9d89-a58e3d610cd6"
        val imageBot2 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-96fdc30e525f75f478d6cf9977234d70.webp?alt=media&token=64122494-b048-4356-b67e-b494d006c1bf"
        val imageBot3 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-c6d6c4477981d833c2732b2ef13b01f8.webp?alt=media&token=ee9688eb-4808-4540-ace6-745e8b993738"
        val imageBot4 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"

        imageList = listOf(imageTop, imageBot1, imageBot2, imageBot3, imageBot4)

        val hotelName = "Classy Boutique Hotel"
        val star = 4.5f
        val address = "123, Phan Dinh Phung, Phu Nhuan, Ho Chi Minh City"
        hotel = Hotel(hotelName, star, address)

        val point = 9.2f
        val count = 580
        rating = Rating(point, count)

        facilitiesList = listOf(
            "ac",
            "restaurant",
            "hour_24",
            "parking",
            "elevator",
            "wifi"
        )

        val policyType1 = "check_in_out"
        val policyType2 = "additional"
        val policyType3 = "general"
        val policiesDescription1 = "Check-in time from 14:00\nCheck-out time before 12:00"
        val policiesDescription2 =
            "Located in the center of Hanoi Old Quarter, newly renovated in 2018, Classy Boutique Hotel is a 5-minute walk from Hoan Kiem Lake and Ngoc Son Temple. It offers free WiFi access and free parking."
        val policiesDescription3 =
            "Please note that Your Children Might Be Charged when check-in at the hotel. Please call the hotel before your check-in date for further information. Vui long luu y, tre em"

        policyList = listOf(
            Policies(policyType1, policiesDescription1),
            Policies(policyType2, policiesDescription2),
            Policies(policyType3, policiesDescription3)
        )

        val descriptionShort1 =
            "Hanoi Chic Boutique Hotel is a hotel in a good neighborhood which is located at Hang Bo Ward."
        val descriptionShort2 =
            "24-hours front desk is available to serve you, from check-in to check-out, or any assistance you need. Should you desire more, do not hesitate to ask the front desk, we are always ready to accommodate you."
        val descriptionShort3 =
            "Wifi is available within public areas of the public area of the property to help you to stay connected with family and friends."
        descriptionList = listOf(descriptionShort1, descriptionShort2, descriptionShort3)
    }

    fun clearData() {
        imageList = null
        hotel = null
        rating = null
        facilitiesList = null
        policyList = null
        descriptionList = null
    }

    fun getDescriptionShort(): String {
        if (descriptionList == null) return ""
        if (descriptionList!!.size != 3) {
            Log.d(TAG, "description list size is not 3")
            return ""
        }

        return "${descriptionList!![0]}\n\n${descriptionList!![1]}\n\n${descriptionList!![2]}"
    }

    fun getTypeRating(): String {
        if (rating == null) {
            Log.d(TAG, "getTypeRating: rating is null")
            return ""
        }

        val point = rating!!.point

        if (point < 0) {
            Log.d(TAG, "getTypeRating: point is negative")
            return ""
        }

        if (point > 10) {
            Log.d(TAG, "getTypeRating: point is greater than 10")
            return ""
        }

        if (point == 0f) return "Poor"
        if (point < 4f) return "Fair"
        if (point < 6f) return "Good"
        if (point < 8f) return "Very Good"
        if (point < 10f) return "Superb"
        return "Excellent"
    }

    fun getCountText(): String {
        if (rating == null) {
            Log.d(TAG, "getCountText: rating is null")
            return ""
        }

        return "From ${rating!!.count} reviews"
    }

}
