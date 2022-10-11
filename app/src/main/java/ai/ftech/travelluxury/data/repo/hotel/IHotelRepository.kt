package ai.ftech.travelluxury.data.repo.hotel

interface IHotelRepository {
    fun getHotelList()
    fun getCityHotelList()
    fun getHotelDetail(hotelId: Int)
    fun getRoomList(hotelId: Int)
    fun getRoomList(hotelId: Int, checkInDate: String, checkOutDate: String)
    fun login(email: String, password: String)
}
