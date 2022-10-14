package ai.ftech.travelluxury.data.source.api

import ai.ftech.travelluxury.data.model.booking.BookingBody
import ai.ftech.travelluxury.data.model.booking.BookingData
import ai.ftech.travelluxury.data.model.history.HistoryData
import ai.ftech.travelluxury.data.model.home.CityHotelData
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailData
import ai.ftech.travelluxury.data.model.hotellist.HotelListData
import ai.ftech.travelluxury.data.model.login.LoginData
import ai.ftech.travelluxury.data.model.payment.PaymentBody
import ai.ftech.travelluxury.data.model.payment.PaymentData
import ai.ftech.travelluxury.data.model.selectroom.SearchRoomBody
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomData
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomData2
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DataService {

    @GET("/v1/home")
    fun getHotelCityList(): Call<CityHotelData>

    @GET("/v1/home/city/hotel/{id}")
    fun getHotelList(@Path("id") hotelId: Int): Call<HotelListData>

    @GET("/v1/home/hotel/detail/{id}")
    fun getHotelDetail(@Path("id") hotelId: Int): Call<HotelDetailData>

    @GET("/v1/home/room/{id}")
    fun getRoomList(@Path("id") hotelId: Int): Call<SelectRoomData>

    @POST("/v1/search/booking/room")
    fun searchRoom(
        @Body body: SearchRoomBody
    ): Call<SelectRoomData2>

    @POST("/v1/login/{userName}/{password}")
    fun login(
        @Path("userName") userName: String,
        @Path("password") password: String
    ): Call<LoginData>

    @POST("/v1/home/hotel/booking")
    fun booking(@Body body: BookingBody): Call<BookingData>

    @POST("/v1/home/hotel/payment")
    fun payment(@Body body: PaymentBody): Call<PaymentData>

    @GET("/v1/home/hotel/list/booking/{id}")
    fun history(@Path("id") userId: Int): Call<HistoryData>
}
