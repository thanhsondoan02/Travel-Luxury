package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.data.calculateCheckOutDate
import ai.ftech.travelluxury.data.model.selectroom.Room
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IHotelRepository
import ai.ftech.travelluxury.data.repo.hotel.IResult

class SelectRoomPresenter : SelectRoomContract.IPresenter {

    var view: SelectRoomActivity? = null

    private val repo: IHotelRepository by lazy {
        HotelRepositoryImpl().apply {
            result = object : IResult {

                @Suppress("UNCHECKED_CAST")
                override fun onRepoSuccess(data: Any) {
                    // update SelectRoomModel room list
                    SelectRoomModel.INSTANCE.roomList = data as List<Room>

                    // update room list in adapter
                    view?.adapter?.roomList = SelectRoomModel.INSTANCE.roomList ?: emptyList()

                    view?.onGetRoomListSuccess()
                }

                override fun onRepoFail(message: String) {
                    view?.onGetRoomListFail(message)
                }

            }
        }
    }

    override fun getRoomListApi() {
        val hotelId = SelectRoomModel.INSTANCE.hotelId
        if (hotelId != null)
            repo.getRoomList(hotelId)
    }

    override fun getSpecialRoomList() {
        view?.adapter?.roomList = emptyList()

        val hotelId = SelectRoomModel.INSTANCE.hotelId
        val checkInDate = SelectRoomModel.INSTANCE.checkInDate
        val duration = SelectRoomModel.INSTANCE.duration

        if (hotelId != null && checkInDate != null && duration != null) {
            val checkOutDate = calculateCheckOutDate(checkInDate, duration)
            repo.getRoomList(hotelId, checkInDate, checkOutDate)
        }
    }

}
