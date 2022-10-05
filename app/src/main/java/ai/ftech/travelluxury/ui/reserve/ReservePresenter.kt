package ai.ftech.travelluxury.ui.reserve

import ai.ftech.travelluxury.data.model.reserve.ReserveModel

class ReservePresenter : IReserveContract.Presenter {
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
}
