package ai.ftech.travelluxury.hotellist

interface HotelListContract {

    interface View {
        fun onGetHotelList(state: HOTEL_LIST_STATE, message: String)
    }

    interface Presenter {
        fun getHotelListApi()
    }

}
