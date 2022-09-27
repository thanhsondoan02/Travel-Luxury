package ai.ftech.travelluxury.model.home

import ai.ftech.travelluxury.main.home.CITY_LIST_STATE

interface HomeContract {
    interface View {
        fun onGetHotelList(state: CITY_LIST_STATE, message: String)
    }

    interface IPresenter {
        fun getHotelListApi()
    }
}
