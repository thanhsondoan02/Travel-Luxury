package ai.ftech.travelluxury.ui.hotellist

import ai.ftech.travelluxury.data.model.hotellist.Hotel
import ai.ftech.travelluxury.data.model.hotellist.HotelListModel
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IHotelRepository

import ai.ftech.travelluxury.data.repo.hotel.IResult


class HotelListPresenter : IHotelListContract.Presenter {

    var view: IHotelListContract.View? = null
    var adapter: HotelListAdapter? = null


    private val hotelRepo: IHotelRepository by lazy {
        HotelRepositoryImpl().apply {
            result = object : IResult {
                @Suppress("UNCHECKED_CAST")
                override fun onRepoSuccess(data: Any) {
                    HotelListModel.INSTANCE.hotelList = data as List<Hotel>
                    adapter?.hotelList = HotelListModel.INSTANCE.hotelList!!

                    view?.onGetHotelListSuccess()
                }


                override fun onRepoFail(message: String) {
                    view?.onGetHotelListFail(message)
                }
            }
        }
    }

    override fun getHotelListApi() {
        view?.showLoading("Searching for hotels")

        val cityId = HotelListModel.INSTANCE.cityId

        if (cityId == null) {
            view?.onGetHotelListFail("City id is null")
        } else {
            hotelRepo.getHotelList()
        }
    }
}
