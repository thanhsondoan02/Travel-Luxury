package ai.ftech.travelluxury.data

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.hotel.FACILITY_TYPE

class HotelFacilitiesHandler : HotelHandler<FACILITY_TYPE> {
    override val map: Map<String, FACILITY_TYPE>
        get() = mapOf(
            "ac" to FACILITY_TYPE.AC,
            "restaurant" to FACILITY_TYPE.RESTAURANT,
            "hour_24" to FACILITY_TYPE.HOUR_24,
            "parking" to FACILITY_TYPE.PARKING,
            "elevator" to FACILITY_TYPE.ELEVATOR,
            "wifi" to FACILITY_TYPE.WIFI
        )

    override fun getIcon(type: FACILITY_TYPE): Int {
        return when (type) {
            FACILITY_TYPE.AC -> R.drawable.ic_ac
            FACILITY_TYPE.RESTAURANT -> R.drawable.ic_restaurant
            FACILITY_TYPE.HOUR_24 -> R.drawable.ic_hour_24
            FACILITY_TYPE.PARKING -> R.drawable.ic_parking
            FACILITY_TYPE.ELEVATOR -> R.drawable.ic_elevator
            FACILITY_TYPE.WIFI -> R.drawable.ic_wifi
        }
    }

    override fun getTitle(type: FACILITY_TYPE): String {
        return when (type) {
            FACILITY_TYPE.AC -> "AC"
            FACILITY_TYPE.RESTAURANT -> "Restaurant"
            FACILITY_TYPE.HOUR_24 -> "24 Hour Front Desk"
            FACILITY_TYPE.PARKING -> "Parking"
            FACILITY_TYPE.ELEVATOR -> "Elevator"
            FACILITY_TYPE.WIFI -> "Wifi"
        }
    }
}



