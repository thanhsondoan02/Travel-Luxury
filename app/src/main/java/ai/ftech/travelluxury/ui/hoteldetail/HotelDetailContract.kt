package ai.ftech.travelluxury.ui.hoteldetail

class HotelDetailContract {

    interface View {
        fun onGetHotelDetail(state: HOTEL_DETAIL_STATE, message: String)
    }

    interface Presenter {
        fun getHotelDetailApi()
    }

}
