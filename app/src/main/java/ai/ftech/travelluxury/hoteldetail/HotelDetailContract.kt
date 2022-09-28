package ai.ftech.travelluxury.hoteldetail

class HotelDetailContract {

    interface View {
        fun onGetHotelDetail(state: HOTEL_DETAIL_STATE, message: String)
    }

    interface Presenter {
        fun getHotelDetailApi()
    }

}
