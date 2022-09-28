package ai.ftech.travelluxury.api

import ai.ftech.travelluxury.model.home.Model
import ai.ftech.travelluxury.model.hotellist.HotelListData
import retrofit2.Call
import retrofit2.http.GET

interface DataService {

    @GET("b74fe13f8f09259a60b0/")
    fun getHotelCityList(): Call<Model>

    @GET("918efc7b7072794ddebc")
    fun getHotelList(): Call<HotelListData>
}
