package ai.ftech.travelluxury.data.model.reserve

import ai.ftech.travelluxury.data.model.selectroom.Room

class ReserveModel {

    companion object {
        val INSTANCE = ReserveModel()
    }

    var room: Room? = null
    var bookingId: Int? = null

    var contactName: String? = null
    var contactPhone: String? = null
    var contactEmail: String? = null

    var specialRequestList = mutableListOf<Pair<SPECIAL_REQUEST, String>>()

    var totalIsOpen: Boolean = false
    var roomName: String? = "Phòng Ở Trong 2 Giờ - Day Use Room Within 02 Hours"
    var roomPrice: Int? = 87759
    var taxName: String? = "Taxes and fees"
    var taxPrice: Int? = 10370

    fun isContactValid(): Boolean {
        return contactName != null && contactPhone != null && contactEmail != null
    }

}
