package ai.ftech.travelluxury.data.repo.hotel

import ai.ftech.travelluxury.data.model.home.CityHotelData
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailData
import ai.ftech.travelluxury.data.model.hotellist.HotelListData
import ai.ftech.travelluxury.data.source.api.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface IRepoResult {
    fun onRepoSuccess(data: Any)
    fun onRepoFail(message: String)
}

class HotelRepositoryImpl : IHotelRepository {

    var callback: IRepoResult? = null

    override fun getHotelList() {
        APIService.base().getHotelList().enqueue(object : MyCallBack<HotelListData>() {
            override fun checkResponseBody(responseBody: HotelListData) {
                if (responseBody.data == null) {
                    callback?.onRepoFail("response body data is null")
                } else {
                    callback?.onRepoSuccess(responseBody.data)
                }
            }
        })
    }

    override fun getCityHotelList() {
        APIService.base().getHotelCityList().enqueue(object : MyCallBack<CityHotelData>() {
            override fun checkResponseBody(responseBody: CityHotelData) {
                if (responseBody.data == null) {
                    callback?.onRepoFail("response body data is null")
                } else {
                    if (responseBody.data.listCity == null) {
                        callback?.onRepoFail("response body data list city is null")
                    } else {
                        callback?.onRepoSuccess(responseBody.data.listCity)
                    }
                }
            }
        })
    }

    override fun getHotelDetail(hotelId: Int) {
        APIService.base().getHotelDetail().enqueue(object : MyCallBack<HotelDetailData>() {
            override fun checkResponseBody(responseBody: HotelDetailData) {
                if (responseBody.data == null) {
                    callback?.onRepoFail("response body data is null")
                } else {
                    callback?.onRepoSuccess(responseBody.data as Any)
                }
            }
        })
    }

    abstract inner class MyCallBack<Data> : Callback<Data> {

        override fun onResponse(call: Call<Data>, response: Response<Data>) {
            if (response.body() == null) {
                callback?.onRepoFail("response body is null")
            } else {
                checkResponseBody(response.body()!!)
            }
        }

        override fun onFailure(call: Call<Data>, t: Throwable) {
            callback?.onRepoFail("Get hotel list failure ${t.message}")
        }

        abstract fun checkResponseBody(responseBody: Data)
    }
}
