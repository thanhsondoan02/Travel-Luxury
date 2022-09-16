package ai.ftech.travelluxury.model

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.hotel.FacilitiesAdapter

class HotelDetail {

    companion object {
        val HOTEL_DETAIL = HotelDetail()
    }

    var imageTop: String? = null
    var imageBot1: String? = null
    var imageBot2: String? = null
    var imageBot3: String? = null
    var imageBot4: String? = null

    var hotelName: String? = null
    var star: Float? = null
    var address: String? = null

    var point: Float? = null
    var type: String? = null
    var count: Int? = null

    var ac: Boolean? = null
    var restaurant: Boolean? = null
    var twentyFourHour: Boolean? = null
    var parking: Boolean? = null
    var elevator: Boolean? = null
    var wifi: Boolean? = null

    var policyType1: String? = null
    var policyType2: String? = null
    var policyType3: String? = null
    var policiesDescription1: String? = null
    var policiesDescription2: String? = null
    var policiesDescription3: String? = null

    var descriptionShort1: String? = null
    var descriptionShort2: String? = null
    var descriptionShort3: String? = null

    fun mockData() {

        imageTop =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        imageBot1 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-8edf3caa310bfe8644baf32ac8a95e46.webp?alt=media&token=2609510c-1f74-4e2d-9d89-a58e3d610cd6"
        imageBot2 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-96fdc30e525f75f478d6cf9977234d70.webp?alt=media&token=64122494-b048-4356-b67e-b494d006c1bf"
        imageBot3 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-c6d6c4477981d833c2732b2ef13b01f8.webp?alt=media&token=ee9688eb-4808-4540-ace6-745e8b993738"
        imageBot4 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"

        hotelName = "Classy Boutique Hotel"
        star = 4.5f
        address = "123, Phan Dinh Phung, Phu Nhuan, Ho Chi Minh City"

        point = 9.2f
        type = "Superb"
        count = 580

        ac = true
        restaurant = true
        twentyFourHour = true
        parking = true
        elevator = true
        wifi = true

        policyType1 = "check_in_out"
        policyType2 = "additional"
        policyType3 = "general"
        policiesDescription1 = "Check-in time from 14:00\nCheck-out time before 12:00"
        policiesDescription2 =
            "Located in the center of Hanoi Old Quarter, newly renovated in 2018, Classy Boutique Hotel is a 5-minute walk from Hoan Kiem Lake and Ngoc Son Temple. It offers free WiFi access and free parking."
        policiesDescription3 =
            "Please note that Your Children Might Be Charged when check-in at the hotel. Please call the hotel before your check-in date for further information. Vui long luu y, tre em"

        descriptionShort1 =
            "Hanoi Chic Boutique Hotel is a hotel in a good neighborhood which is located at Hang Bo Ward."
        descriptionShort2 =
            "24-hours front desk is available to serve you, from check-in to check-out, or any assistance you need. Should you desire more, do not hesitate to ask the front desk, we are always ready to accommodate you."
        descriptionShort3 =
            "Wifi is available within public areas of the public area of the property to help you to stay connected with family and friends."
    }

    fun getFacilitiesList(): MutableList<FacilitiesAdapter.FacilityData> {
        val list = mutableListOf<FacilitiesAdapter.FacilityData>()

        if (ac == true)
            list.add(
                FacilitiesAdapter.FacilityData(
                    R.drawable.ic_ac,
                    "AC"
                )
            )

        if (restaurant == true)
            list.add(
                FacilitiesAdapter.FacilityData(
                    R.drawable.ic_restaurant,
                    "Restaurant"
                )
            )

        if (twentyFourHour == true)
            list.add(
                FacilitiesAdapter.FacilityData(
                    R.drawable.ic_24_hour,
                    "24 Hour Front Desk"
                )
            )

        if (parking == true)
            list.add(
                FacilitiesAdapter.FacilityData(
                    R.drawable.ic_parking,
                    "Parking"
                )
            )

        if (elevator == true)
            list.add(
                FacilitiesAdapter.FacilityData(
                    R.drawable.ic_elevator,
                    "Elevator"
                )
            )

        if (wifi == true)
            list.add(
                FacilitiesAdapter.FacilityData(
                    R.drawable.ic_wifi,
                    "Wifi"
                )
            )

        return list
    }

    fun clearData() {
        imageTop = null
        imageBot1 = null
        imageBot2 = null
        imageBot3 = null
        imageBot4 = null

        hotelName = null
        star = null
        address = null

        point = null
        type = null
        count = null

        ac = null
        restaurant = null
        twentyFourHour = null
        parking = null
        elevator = null
        wifi = null

        policyType1 = null
        policyType2 = null
        policyType3 = null
        policiesDescription1 = null
        policiesDescription2 = null
        policiesDescription3 = null
    }

    fun getDescriptionShort(): String {
        return "$descriptionShort1\n\n$descriptionShort2\n\n$descriptionShort3"
    }

}
