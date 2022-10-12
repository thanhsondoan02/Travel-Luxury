package ai.ftech.travelluxury.ui.payment

import ai.ftech.travelluxury.data.calculateCheckOutDate
import ai.ftech.travelluxury.data.model.login.AccountData
import ai.ftech.travelluxury.data.model.reserve.ReserveModel
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IHotelRepository
import ai.ftech.travelluxury.data.repo.hotel.IResult

class PaymentPresenter : PaymentContract.Presenter {

    var view: PaymentActivity? = null

    private val repo: IHotelRepository by lazy {
        HotelRepositoryImpl().apply {
            result = object : IResult {
                override fun onRepoSuccess(data: Any) {
                    view?.onPaymentSuccess()
                }

                override fun onRepoFail(message: String) {
                    view?.onPaymentFail()
                }
            }
        }
    }

    override fun handlePayment() {
        val bookingId = ReserveModel.INSTANCE.bookingId!!
        val userId = AccountData.INSTANCE?.id!!
        val roomId = ReserveModel.INSTANCE.room?.id!!
        val checkIn = SelectRoomModel.INSTANCE.checkInDate!!
        val checkOut = calculateCheckOutDate(
            SelectRoomModel.INSTANCE.checkInDate!!,
            SelectRoomModel.INSTANCE.duration!!
        )
        val price = ReserveModel.INSTANCE.room?.price!!
        repo.payment(bookingId, userId, roomId, checkIn, checkOut, price)
    }

}
