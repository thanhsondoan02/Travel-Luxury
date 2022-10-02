package ai.ftech.travelluxury.data.repo.hotel

import ai.ftech.travelluxury.data.model.hotellist.Hotel
import ai.ftech.travelluxury.data.source.api.APIService

class HotelRepositoryImpl : IHotelRepository {

    override fun getHotelList(cityId: Int): List<Hotel> {
        val response = APIService.base().getHotelList(cityId).execute()

        if (response.isSuccessful) {
            if (response.body() != null) {
                if (response.body()!!.data != null) {
                    return response.body()!!.data!!
                } else {
                    throw RuntimeException("response body data is null")
                }
            } else {
                throw RuntimeException("response body is null")
            }
        } else {
            throw RuntimeException("response is not successful")
        }
    }
}
