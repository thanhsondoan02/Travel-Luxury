package ai.ftech.travelluxury.data.source.api

import ai.ftech.travelluxury.data.model.home.CityHotelData
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailData
import ai.ftech.travelluxury.data.model.hotellist.HotelListData
import retrofit2.Call
import retrofit2.http.GET

interface DataService {

    @GET("b74fe13f8f09259a60b0/")
    fun getHotelCityList(): Call<CityHotelData>

    @GET("918efc7b7072794ddebc")
    fun getHotelList(): Call<HotelListData>

    @GET("57389369118b59df9d1a")
    fun getHotelDetail(): Call<HotelDetailData>

    @GET()
    fun getRoomList(hotelId: Int): Call<HotelDetailData>
}
