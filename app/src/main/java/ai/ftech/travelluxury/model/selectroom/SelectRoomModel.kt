package ai.ftech.travelluxury.model.selectroom

import ai.ftech.travelluxury.model.hoteldetail.HotelDetail.Companion.HOTEL_DETAIL

class SelectRoomModel {

    companion object {
        val SELECT_ROOM_MODEL = SelectRoomModel()
    }

    var hotelName: String? = null
    var hotelAddress: String? = null

    var roomList: List<Room>? = null

    fun mockData() {
        val image1 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-76da33ac4a4d1e377f606253e67c93c6.webp?alt=media&token=60bcac5f-9ecb-48a0-b6d8-36a03f542d2c"
        val image2 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-8edf3caa310bfe8644baf32ac8a95e46.webp?alt=media&token=2609510c-1f74-4e2d-9d89-a58e3d610cd6"
        val image3 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-96fdc30e525f75f478d6cf9977234d70.webp?alt=media&token=64122494-b048-4356-b67e-b494d006c1bf"
        val image4 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-c6d6c4477981d833c2732b2ef13b01f8.webp?alt=media&token=ee9688eb-4808-4540-ace6-745e8b993738"
        val image5 =
            "https://firebasestorage.googleapis.com/v0/b/travel-luxury-4f5df.appspot.com/o/20011770-d2f82cec329fdbf93b1a2ff5e84a33b3.webp?alt=media&token=6bfb1727-f29d-437d-a8db-fb5fda0b3503"
        val imageList1 = listOf(image1, image2, image3, image4, image5)
        val imageList2 = listOf(image2, image3, image4, image5, image1)
        val imageList3 = listOf(image3, image4, image5, image1, image2)
        val imageList4 = listOf(image4, image5, image1, image2, image3)
        val imageList5 = listOf(image5, image1, image2, image3, image4)

        val room1 = Room(
            0,
            "Classic",
            2,
            "1 double bed or 2 single bed",
            "Breakfast not included",
            "Non refundable",
            522576,
            imageList1
        )
        val room2 = Room(
            0,
            "Classic",
            2,
            "1 double bed or 2 single bed",
            "Breakfast not included",
            "Non refundable",
            8121568,
            imageList2
        )

        val room3 = Room(
            0,
            "Superior",
            2,
            "1 double bed or 2 single bed",
            "Breakfast not included",
            "Non refundable",
            522576,
            imageList3
        )
        val room4 = Room(
            0,
            "Superior",
            2,
            "1 double bed or 2 single bed",
            "Breakfast not included",
            "Non refundable",
            522576,
            imageList4
        )
        val room5 = Room(
            0,
            "Superior",
            2,
            "1 double bed or 2 single bed",
            "Breakfast not included",
            "Non refundable",
            522576,
            imageList5
        )
        val room6 = Room(
            0,
            "Superior",
            2,
            "1 double bed or 2 single bed",
            "Breakfast not included",
            "Non refundable",
            522576,
            imageList1
        )
        val room7 = Room(
            0,
            "Superior",
            2,
            "1 double bed or 2 single bed",
            "Breakfast not included",
            "Non refundable",
            522576,
            imageList2
        )
        val room8 = Room(
            0,
            "Superior",
            2,
            "1 double bed or 2 single bed",
            "Breakfast not included",
            "Non refundable",
            522576,
            imageList3
        )

        roomList = listOf(room1, room2, room3, room4, room5, room6, room7, room8)
    }

    fun getGuessString(number: Int): String {
        return "$number guest(s)/room"
    }

    fun fromHotelDetail() {
        this.hotelName = HOTEL_DETAIL.hotel!!.hotelName
        this.hotelAddress = HOTEL_DETAIL.hotel!!.address
    }

}
