package ai.ftech.travelluxury.main.home

interface HomeContract {
    interface View {
        fun onGetHotelList(state: CITY_HOTEL_STATE, message: String)
    }

    interface IPresenter {
        fun getHotelListApi()
    }
}
