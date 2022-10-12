package ai.ftech.travelluxury.ui.reserve

interface IReserveContract {

    interface View {
        fun onBookingSuccess()
        fun onBookingFail()
    }

    interface Presenter {
        fun onTotalPriceClick()
        fun handleBooking()
    }

}
