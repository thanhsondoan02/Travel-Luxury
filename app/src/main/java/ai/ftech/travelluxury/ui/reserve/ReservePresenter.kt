package ai.ftech.travelluxury.ui.reserve

import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.calculateCheckOutDate
import ai.ftech.travelluxury.data.formatDate
import ai.ftech.travelluxury.data.model.booking.BookingData
import ai.ftech.travelluxury.data.model.login.AccountData
import ai.ftech.travelluxury.data.model.reserve.ReserveModel
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IHotelRepository
import ai.ftech.travelluxury.data.repo.hotel.IResult
import android.util.Log

class ReservePresenter : IReserveContract.Presenter {

    var view: ReserveActivity? = null

    private val repo: IHotelRepository by lazy {
        HotelRepositoryImpl().apply {
            result = object : IResult {
                override fun onRepoSuccess(data: Any) {
                    val bookingData = data as BookingData
                    val bookingList = bookingData.data!!

                    ReserveModel.INSTANCE.bookingId = bookingList[bookingList.size - 1].bookingId
                    Log.d(TAG, "bookingId: ")

                    view?.onBookingSuccess()
                }

                override fun onRepoFail(message: String) {
                    view?.onBookingFail()
                }

            }
        }
    }

    override fun onTotalPriceClick() {
        ReserveModel.INSTANCE.totalIsOpen = !ReserveModel.INSTANCE.totalIsOpen

        if (ReserveModel.INSTANCE.totalIsOpen) {
            ReserveModel.INSTANCE.roomName = "Phòng Ở Trong 2 Giờ - Day Use Room Within 02 Hours"
            ReserveModel.INSTANCE.roomPrice = 87759
            ReserveModel.INSTANCE.taxName = "Taxes and fees"
            ReserveModel.INSTANCE.taxPrice = 10370
        } else {
            ReserveModel.INSTANCE.roomName = ""
            ReserveModel.INSTANCE.roomPrice = null
            ReserveModel.INSTANCE.taxName = ""
            ReserveModel.INSTANCE.taxPrice = null
        }
    }

    override fun handleBooking() {
        val userId = AccountData.INSTANCE?.id!!
        val roomId = ReserveModel.INSTANCE.room?.id!!
        val checkIn = SelectRoomModel.INSTANCE.checkInDate!!
        val checkOut = calculateCheckOutDate(
            SelectRoomModel.INSTANCE.checkInDate!!,
            SelectRoomModel.INSTANCE.duration!!
        )

        repo.booking(userId, roomId, formatDate(checkIn), formatDate(checkOut))
    }

}
