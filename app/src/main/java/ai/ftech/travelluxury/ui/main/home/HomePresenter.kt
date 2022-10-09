package ai.ftech.travelluxury.ui.main.home

import ai.ftech.travelluxury.data.model.home.City
import ai.ftech.travelluxury.data.model.home.HomeModel
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IResult

class HomePresenter : HomeContract.IPresenter {

    var view: HomeFragment? = null

    private val hotelRepo by lazy {
        HotelRepositoryImpl().apply {
            result = object : IResult {

                @Suppress("UNCHECKED_CAST")
                override fun onRepoSuccess(data: Any) {
                    // update Home Model city list
                    HomeModel.INSTANCE.cityList = data as List<City>

                    // update city list in adapter
                    val adapter = view?.homeAdapter?.cityListVH?.adapter
                    adapter?.cityList = HomeModel.INSTANCE.cityList ?: listOf()

                    view?.onGetHotelCityListSuccess()
                }

                override fun onRepoFail(message: String) {
                    view?.onGetHotelCityListFail(message)
                }

            }
        }

    }

    override fun getHotelCityListApi() {
        hotelRepo.getCityHotelList()
    }

}
