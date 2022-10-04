package ai.ftech.travelluxury.ui.main.home

import ai.ftech.travelluxury.data.model.home.City
import ai.ftech.travelluxury.data.model.home.HomeModel
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IRepoResult

class HomePresenter : HomeContract.IPresenter {

    var view: HomeFragment? = null

    private val hotelRepo by lazy {
        HotelRepositoryImpl().apply {
            callback = object : IRepoResult {
                @Suppress("UNCHECKED_CAST")
                override fun onRepoSuccess(data: Any) {
                    // update Home Model city list
                    HomeModel.INSTANCE.cityList = data as List<City>

                    // update view hotel city list
                    view?.homeAdapter?.cityListVH?.adapter?.cityList =
                        HomeModel.INSTANCE.cityList ?: listOf()

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
