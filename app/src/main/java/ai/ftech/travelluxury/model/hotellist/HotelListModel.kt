package ai.ftech.travelluxury.model.hotellist

class HotelListModel {

    companion object {
        val HOTEL_LIST_MODEL = HotelListModel()
    }

    var cityId: Int? = null
    var cityName: String? = null
    var hotelList: List<Hotel>? = null

    fun mockData() {
        val hotel1 = Hotel(
            "Muong Thanh Luxury Saigon Hotel",
            2.5f,
            "Ward 10, Ho Chi Minh City",
            8.5f,
            234,
            1636992,
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        )
        val hotel2 = Hotel(
            "Muong Thanh Luxury Saigon Hotel",
            5.0f,
            "Ward 10, Ho Chi Minh City",
            8.5f,
            234,
            1636992,
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        )
        val hotel3 = Hotel(
            "Muong Thanh Luxury Saigon Hotel",
            1.0f,
            "Ward 10, Ho Chi Minh City",
            8.5f,
            234,
            1636992,
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        )
        val hotel4 = Hotel(
            "Muong Thanh Luxury Saigon Hotel",
            4.5f,
            "Ward 10, Ho Chi Minh City",
            8.5f,
            234,
            1636992,
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        )
        val hotel5 = Hotel(
            "Muong Thanh Luxury Saigon Hotel",
            2.5f,
            "Ward 10, Ho Chi Minh City",
            8.5f,
            234,
            1636992,
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        )
        val hotel6 = Hotel(
            "Muong Thanh Luxury Saigon Hotel",
            2.5f,
            "Ward 10, Ho Chi Minh City",
            8.5f,
            234,
            1636992,
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        )
        val hotel7 = Hotel(
            "Muong Thanh Luxury Saigon Hotel",
            2.5f,
            "Ward 10, Ho Chi Minh City",
            8.5f,
            234,
            1636992,
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        )
        val hotel8 = Hotel(
            "Muong Thanh Luxury Saigon Hotel",
            2.5f,
            "Ward 10, Ho Chi Minh City",
            8.5f,
            234,
            1636992,
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        )
        val hotel9 = Hotel(
            "Muong Thanh Luxury Saigon Hotel",
            2.5f,
            "Ward 10, Ho Chi Minh City",
            8.5f,
            234,
            1636992,
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        )
        val hotel10 = Hotel(
            "Muong Thanh Luxury Saigon Hotel",
            2.5f,
            "Ward 10, Ho Chi Minh City",
            8.5f,
            234,
            1636992,
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        )

        hotelList =
            listOf(hotel1, hotel2, hotel3, hotel4, hotel5, hotel6, hotel7, hotel8, hotel9, hotel10)

    }

}
