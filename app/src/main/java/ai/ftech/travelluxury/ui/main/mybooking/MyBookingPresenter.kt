package ai.ftech.travelluxury.ui.main.mybooking

import ai.ftech.travelluxury.data.model.history.HistoryData
import ai.ftech.travelluxury.data.model.login.AccountData
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IHotelRepository
import ai.ftech.travelluxury.data.repo.hotel.IResult

class MyBookingPresenter : MyBookingContract.Presenter {

    var view: MyBookingContract.View? = null

    @Suppress("UNCHECKED_CAST")
    private val repo: IHotelRepository by lazy {
        HotelRepositoryImpl().apply {
            result = object : IResult {
                override fun onRepoSuccess(data: Any) {
                    val bookingData = data as HistoryData
                    val bookingList = bookingData.data!!
                    view?.onHistorySuccess(bookingList)
                }

                override fun onRepoFail(message: String) {
                    view?.onHistoryFail(message)
                }
            }
        }
    }

    override fun handleHistory() {
        repo.history(AccountData.INSTANCE?.id!!)
    }

}
