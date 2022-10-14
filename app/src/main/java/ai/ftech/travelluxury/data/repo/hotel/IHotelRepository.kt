package ai.ftech.travelluxury.data.repo.hotel

interface IHotelRepository {
    fun getHotelList(hotelId: Int)
    fun getCityHotelList()
    fun getHotelDetail(hotelId: Int)
    fun getRoomList(hotelId: Int)
    fun getRoomList(hotelId: Int, checkInDate: String, checkOutDate: String)
    fun login(email: String, password: String)
    fun booking(userId: Int, roomId: Int, checkIn: String, checkOut: String)
    fun payment(
        bookingId: Int,
        userId: Int,
        roomId: Int,
        checkIn: String,
        checkOut: String,
        price: Int
    )

    fun history(userId: Int)
}
