package ai.ftech.travelluxury.ui.main.home

interface HomeContract {
    interface View {
        fun onGetHotelCityListSuccess()
        fun onGetHotelCityListFail(message: String)
    }

    interface IPresenter {
        fun getHotelCityListApi()
    }
}
