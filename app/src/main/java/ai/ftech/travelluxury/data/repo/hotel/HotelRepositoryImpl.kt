package ai.ftech.travelluxury.data.repo.hotel

import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.model.booking.BookingData
import ai.ftech.travelluxury.data.model.history.HistoryData
import ai.ftech.travelluxury.data.model.home.CityHotelData
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailData
import ai.ftech.travelluxury.data.model.hotellist.HotelListData
import ai.ftech.travelluxury.data.model.login.LoginData
import ai.ftech.travelluxury.data.model.payment.PaymentData
import ai.ftech.travelluxury.data.model.selectroom.Room
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomData
import ai.ftech.travelluxury.data.source.api.APIService
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HotelRepositoryImpl : IHotelRepository {

    var result: IResult? = null

    override fun getHotelList() {
        APIService.base().getHotelList().enqueue(object : MyCallBack<HotelListData>() {
            override fun checkResponseBody(responseBody: HotelListData) {
                if (responseBody.data == null) {
                    result?.onRepoFail("response body data is null")
                } else {
                    result?.onRepoSuccess(responseBody.data)
                }
            }
        })
    }

    override fun getCityHotelList() {
        APIService.base().getHotelCityList().enqueue(object : MyCallBack<CityHotelData>() {
            override fun checkResponseBody(responseBody: CityHotelData) {
                if (responseBody.data == null) {
                    result?.onRepoFail("response body data is null")
                } else {
                    if (responseBody.data.listCity == null) {
                        result?.onRepoFail("response body data list city is null")
                    } else {
                        result?.onRepoSuccess(responseBody.data.listCity)
                    }
                }
            }
        })
    }

    override fun getHotelDetail(hotelId: Int) {
        APIService.base().getHotelDetail().enqueue(object : MyCallBack<HotelDetailData>() {
            override fun checkResponseBody(responseBody: HotelDetailData) {
                if (responseBody.data == null) {
                    result?.onRepoFail("response body data is null")
                } else {
                    result?.onRepoSuccess(responseBody.data as Any)
                }
            }
        })
    }

    override fun getRoomList(hotelId: Int) {
        APIService.base().getRoomList().enqueue(object : MyCallBack<SelectRoomData>() {
            override fun checkResponseBody(responseBody: SelectRoomData) {
                if (responseBody.data == null) {
                    result?.onRepoFail("response body data is null")
                } else {
                    val listOfRoomAndImageList = responseBody.data
                    if (listOfRoomAndImageList == null) {
                        result?.onRepoFail("response body data list of room and image list is null")
                    } else {
                        val listOfRoom = mutableListOf<Room>()
                        for (roomAndImageList in listOfRoomAndImageList) {
                            val room = roomAndImageList.room
                            val mImageList = roomAndImageList.imageList
                            if (room != null && mImageList != null) {
                                room.imageList = mImageList
                                listOfRoom.add(room)
                            }
                        }

                        result?.onRepoSuccess(listOfRoom as List<Room>)
                    }
                }
            }
        })
    }

    override fun getRoomList(hotelId: Int, checkInDate: String, checkOutDate: String) {
        APIService.base().searchRoom().enqueue(object : MyCallBack<SelectRoomData>() {
            override fun checkResponseBody(responseBody: SelectRoomData) {
                if (responseBody.data == null) {
                    result?.onRepoFail("response body data is null")
                } else {
                    val listOfRoomAndImageList = responseBody.data
                    if (listOfRoomAndImageList == null) {
                        result?.onRepoFail("response body data list of room and image list is null")
                    } else {
                        val listOfRoom = mutableListOf<Room>()
                        for (roomAndImageList in listOfRoomAndImageList) {
                            val room = roomAndImageList.room
                            val mImageList = roomAndImageList.imageList
                            if (room != null && mImageList != null) {
                                room.imageList = mImageList
                                listOfRoom.add(room)
                            }
                        }

                        result?.onRepoSuccess(listOfRoom as List<Room>)
                    }
                }
            }
        })
    }

    override fun login(email: String, password: String) {
        APIService.base().login().enqueue(object : MyCallBack<LoginData>() {
            override fun checkResponseBody(responseBody: LoginData) {
                if (responseBody.message == null) {
                    Log.d(TAG, "login: response body message is null")
                    result?.onRepoFail("connect to server fail")
                } else {
                    if (responseBody.message != "Login Success") {
                        Log.d(TAG, "login: server message = ${responseBody.message}")
                        result?.onRepoFail(responseBody.message!!)
                    } else {
                        if (responseBody.data == null) {
                            Log.d(TAG, "login: response body data is null")
                            result?.onRepoFail("connect to server fail")
                        } else {
                            result?.onRepoSuccess(responseBody.data!!)
                        }
                    }
                }
            }
        })
    }

    override fun booking(userId: Int, roomId: Int, checkIn: String, checkOut: String) {
        APIService.base().booking().enqueue(object : MyCallBack<BookingData>() {
            override fun checkResponseBody(responseBody: BookingData) {
                if (responseBody.message == null) {
                    Log.d(TAG, "booking: response body message is null")
                    result?.onRepoFail("connect to server fail")
                } else {
                    if (responseBody.message != "Success") {
                        Log.d(TAG, "booking: server message = ${responseBody.message}")
                        result?.onRepoFail(responseBody.message!!)
                    } else {
                        result?.onRepoSuccess(responseBody)
                    }
                }
            }
        })
    }

    override fun payment(
        bookingId: Int,
        userId: Int,
        roomId: Int,
        checkIn: String,
        checkOut: String,
        price: Int
    ) {
        APIService.base().payment().enqueue(object : MyCallBack<PaymentData>() {
            override fun checkResponseBody(responseBody: PaymentData) {
                if (responseBody.message == null) {
                    Log.d(TAG, "payment: response body message is null")
                    result?.onRepoFail("connect to server fail")
                } else {
                    if (responseBody.message != "Success") {
                        Log.d(TAG, "payment: server message = ${responseBody.message}")
                        result?.onRepoFail(responseBody.message!!)
                    } else {
                        result?.onRepoSuccess("")
                    }
                }
            }
        })
    }

    override fun history(userId: Int) {
        APIService.base().history().enqueue(object : MyCallBack<HistoryData>() {
            override fun checkResponseBody(responseBody: HistoryData) {
                if (responseBody.message == null) {
                    Log.d(TAG, "history: response body message is null")
                    result?.onRepoFail("connect to server fail")
                } else {
                    if (responseBody.message != "Success") {
                        Log.d(TAG, "history: server message = ${responseBody.message}")
                        result?.onRepoFail(responseBody.message!!)
                    } else {
                        result?.onRepoSuccess(responseBody)
                    }
                }
            }
        })
    }

    abstract inner class MyCallBack<Data> : Callback<Data> {

        override fun onResponse(call: Call<Data>, response: Response<Data>) {
            if (response.body() == null) {
                result?.onRepoFail("response body is null")
            } else {
                checkResponseBody(response.body()!!)
            }
        }

        override fun onFailure(call: Call<Data>, t: Throwable) {
            result?.onRepoFail("Get hotel list failure ${t.message}")
        }

        abstract fun checkResponseBody(responseBody: Data)
    }
}
