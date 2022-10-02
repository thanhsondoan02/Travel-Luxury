package ai.ftech.travelluxury.ui.hotellist

import ai.ftech.travelluxury.data.model.hotellist.HotelListData

interface HotelListContract {

    interface View {
        fun onGetHotelListSuccess()
        fun onGetHotelListFail(message: String?)
    }

    interface Presenter {
        fun getHotelListApi()
    }
}
