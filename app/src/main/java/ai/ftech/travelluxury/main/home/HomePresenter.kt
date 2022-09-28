package ai.ftech.travelluxury.main.home

import ai.ftech.travelluxury.api.APIService
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.model.home.HomeModel.Companion.HOME_MODEL
import ai.ftech.travelluxury.model.home.Model
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(private val view: HomeContract.View) : HomeContract.IPresenter {

    override fun getHotelCityListApi() {
        APIService.base().getHotelCityList().enqueue(object : Callback<Model> {

            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                val model = response.body()
                val cityList = model?.data?.listCity

                if (cityList != null) {
                    HOME_MODEL.cityList = cityList
                    view.onGetHotelCityList(CITY_HOTEL_STATE.SUCCESS, "Get hotel list success")
                } else {
                    view.onGetHotelCityList(CITY_HOTEL_STATE.FAILURE, "Get hotel list failure")
                }
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                Log.d(TAG, "onFailure: hotel list api fail")
                Log.d(TAG, "onFailure: ${t.message}")
                view.onGetHotelCityList(CITY_HOTEL_STATE.FAILURE, "Get hotel list failure")
            }
        }
        )
    }

}
