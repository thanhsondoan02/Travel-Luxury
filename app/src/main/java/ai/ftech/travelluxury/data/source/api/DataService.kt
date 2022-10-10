package ai.ftech.travelluxury.data.source.api

import ai.ftech.travelluxury.data.model.home.CityHotelData
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailData
import ai.ftech.travelluxury.data.model.hotellist.HotelListData
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomData
import retrofit2.Call
import retrofit2.http.GET

interface DataService {

    @GET("b74fe13f8f09259a60b0/")
    fun getHotelCityList(): Call<CityHotelData>

    @GET("918efc7b7072794ddebc")
    fun getHotelList(): Call<HotelListData>

    @GET("57389369118b59df9d1a")
    fun getHotelDetail(): Call<HotelDetailData>

    @GET("bc8daa907b3c880041e9")
    fun getRoomList(): Call<SelectRoomData>

    @GET("aa782fde0b30f3aa3cdf")
    fun searchRoom(): Call<SelectRoomData>
}
