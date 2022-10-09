package ai.ftech.travelluxury.ui.selectroom

interface SelectRoomContract {

    interface IView {
        fun onGetRoomListSuccess()
        fun onGetRoomListFail(message: String)
    }

    interface IPresenter {
        fun getRoomListApi()
    }

}
