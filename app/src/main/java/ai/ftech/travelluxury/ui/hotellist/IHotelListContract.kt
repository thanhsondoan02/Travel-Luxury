package ai.ftech.travelluxury.ui.hotellist

import ai.ftech.travelluxury.common.IBaseView

interface IHotelListContract {

    interface View : IBaseView {
        fun onGetHotelListSuccess()
        fun onGetHotelListFail(message: String?)
    }

    interface Presenter {
        fun getHotelListApi()
    }
}
