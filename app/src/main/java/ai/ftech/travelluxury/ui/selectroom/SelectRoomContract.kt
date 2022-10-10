package ai.ftech.travelluxury.ui.selectroom

interface SelectRoomContract {

    interface IView {
        fun onGetRoomListSuccess()
        fun onGetRoomListFail(message: String)
        fun onCheckInDateChange()
        fun onDurationChange()
        fun onRoomListChange()
    }

    interface IPresenter {
        fun getRoomListApi()
        fun getSpecialRoomList()
    }

}
