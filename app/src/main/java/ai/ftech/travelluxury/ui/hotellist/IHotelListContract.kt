package ai.ftech.travelluxury.ui.hotellist

interface IHotelListContract {

    interface View {
        fun onGetHotelListSuccess()
        fun onGetHotelListFail(message: String?)
    }

    interface Presenter {
        fun getHotelListApi()
    }
}
