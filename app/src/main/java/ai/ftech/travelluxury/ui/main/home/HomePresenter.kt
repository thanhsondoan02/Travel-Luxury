package ai.ftech.travelluxury.ui.main.home

import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.model.home.City
import ai.ftech.travelluxury.data.model.home.HomeModel
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IResult
import android.util.Log

const val DOMESTIC_CITY_NUMBER = 16

class HomePresenter : HomeContract.IPresenter {

    var view: HomeFragment? = null

    override fun getDomesticCityList() {
        val hotelRepo = HotelRepositoryImpl().apply {
            result = object : IResult {

                @Suppress("UNCHECKED_CAST")
                override fun onRepoSuccess(data: Any) {
                    // update Home Model city list
                    HomeModel.INSTANCE.cityList = data as List<City>

                    // update city list in adapter
                    val cityListAdapter = view?.homeAdapter?.cityListVH?.adapter
                    cityListAdapter?.cityList =
                        getDomesticList(HomeModel.INSTANCE.cityList ?: listOf())

                    view?.onGetHotelCityListSuccess()
                }

                override fun onRepoFail(message: String) {
                    view?.onGetHotelCityListFail(message)
                }

            }
        }

        Log.d(TAG, "getDomesticCityList: ")

        hotelRepo.getCityHotelList()
    }

    override fun getInternationalCityList() {
        val hotelRepo = HotelRepositoryImpl().apply {
            result = object : IResult {

                @Suppress("UNCHECKED_CAST")
                override fun onRepoSuccess(data: Any) {
                    // update Home Model city list
                    HomeModel.INSTANCE.cityList = data as List<City>

                    // update city list in adapter
                    val cityListAdapter = view?.homeAdapter?.cityListVH?.adapter
                    cityListAdapter?.cityList =
                        getInternationalList(HomeModel.INSTANCE.cityList ?: listOf())

                    view?.onGetHotelCityListSuccess()
                }

                override fun onRepoFail(message: String) {
                    view?.onGetHotelCityListFail(message)
                }

            }
        }

        hotelRepo.getCityHotelList()
    }

    private fun getDomesticList(cities: List<City>): List<City> {
        val ans = mutableListOf<City>()

        for (i in 0 until DOMESTIC_CITY_NUMBER) {
            ans.add(cities[i])
        }

        return ans
    }

    private fun getInternationalList(cities: List<City>): List<City> {
        val ans = mutableListOf<City>()

        for (i in DOMESTIC_CITY_NUMBER until cities.size) {
            ans.add(cities[i])
        }

        return ans
    }

}
