package ai.ftech.travelluxury.ui.main.mybooking

import ai.ftech.travelluxury.data.model.history.Booking

interface MyBookingContract {

    interface View {
        fun onHistorySuccess(data: List<Booking>)
        fun onHistoryFail(message: String)
    }

    interface Presenter {
        fun handleHistory()
    }

}
