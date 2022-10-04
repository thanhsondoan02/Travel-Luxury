package ai.ftech.travelluxury.ui.hoteldetail

class HotelDetailContract {

    interface View {
        fun onGetHotelDetailSuccess()
        fun onGetHotelDetailFail(message: String)
    }

    interface Presenter {
        fun getHotelDetailApi()
    }

}
