package ai.ftech.travelluxury.data.source.api

import ai.ftech.travelluxury.data.model.booking.BookingData
import ai.ftech.travelluxury.data.model.history.HistoryData
import ai.ftech.travelluxury.data.model.home.CityHotelData
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailData
import ai.ftech.travelluxury.data.model.hotellist.HotelListData
import ai.ftech.travelluxury.data.model.login.LoginData
import ai.ftech.travelluxury.data.model.payment.PaymentData
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

    @GET("3705e25edef8c20b7123")
    fun login(): Call<LoginData>

    @GET("82f71a728dafbfbbb4a0")
    fun booking(): Call<BookingData>

    @GET("6577e0c60d2fcc55356d")
    fun payment(): Call<PaymentData>

    @GET("ac11ca2d124ebf91c11a")
    fun history(): Call<HistoryData>
}
