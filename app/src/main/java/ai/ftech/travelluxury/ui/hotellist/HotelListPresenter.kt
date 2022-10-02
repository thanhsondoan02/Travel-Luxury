package ai.ftech.travelluxury.ui.hotellist

import ai.ftech.travelluxury.data.model.hotellist.HotelListModel
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IHotelRepository

class HotelListPresenter(private val view: HotelListContract.View) : HotelListContract.Presenter {

    private val hotelRepo: IHotelRepository by lazy {
        HotelRepositoryImpl()
    }

    override fun getHotelListApi() {
        val cityId = HotelListModel.INSTANCE.cityId

        if (cityId == null) {
            view.onGetHotelListFail("City id is null")
        } else {
            try {
                val list = hotelRepo.getHotelList(cityId)
                HotelListModel.INSTANCE.hotelList = list
                view.onGetHotelListSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                view.onGetHotelListFail(e.message)
            }
        }
    }
}
