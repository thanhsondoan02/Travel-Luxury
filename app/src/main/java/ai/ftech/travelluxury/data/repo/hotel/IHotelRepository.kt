package ai.ftech.travelluxury.data.repo.hotel

interface IHotelRepository {
    fun getHotelList()
    fun getCityHotelList()
    fun getHotelDetail(hotelId: Int)
}
