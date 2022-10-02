package ai.ftech.travelluxury.ui.main.home

interface HomeContract {
    interface View {
        fun onGetHotelCityList(state: CITY_HOTEL_STATE, message: String)
    }

    interface IPresenter {
        fun getHotelCityListApi()
    }
}
