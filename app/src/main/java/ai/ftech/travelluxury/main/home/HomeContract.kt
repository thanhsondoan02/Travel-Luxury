package ai.ftech.travelluxury.main.home

interface HomeContract {
    interface View {
        fun onGetHotelList(state: CITY_LIST_STATE, message: String)
    }

    interface IPresenter {
        fun getHotelListApi()
    }
}
