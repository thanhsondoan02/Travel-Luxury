package ai.ftech.travelluxury.data.repo.hotel

import ai.ftech.travelluxury.data.model.hotellist.Hotel

interface IHotelRepository {
    fun getHotelList(cityId: Int): List<Hotel>
}
