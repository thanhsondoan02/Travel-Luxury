package ai.ftech.travelluxury.hotellist

import ai.ftech.travelluxury.api.APIService
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.model.hotellist.HotelListData
import ai.ftech.travelluxury.model.hotellist.HotelListModel.Companion.HOTEL_LIST_MODEL
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelListPresenter(private val view: HotelListContract.View) : HotelListContract.Presenter {

    override fun getHotelListApi() {

        val cityId = HOTEL_LIST_MODEL.cityId

        if (cityId == null) {
            view.onGetHotelList(HOTEL_LIST_STATE.NULL_CITY_ID, "City id is null")
        } else {

            APIService.base().getHotelList().enqueue(object : Callback<HotelListData> {

                override fun onResponse(
                    call: Call<HotelListData>,
                    response: Response<HotelListData>
                ) {
                    val hotelListData = response.body()

                    if (hotelListData != null) {
                        HOTEL_LIST_MODEL.hotelList = hotelListData.data
                        Log.d(TAG, "onResponse: ${HOTEL_LIST_MODEL.hotelList?.get(0)?.name}")
                        view.onGetHotelList(HOTEL_LIST_STATE.SUCCESS, "Api on success")
                    } else {
                        view.onGetHotelList(
                            HOTEL_LIST_STATE.API_ON_RESPONSE_FAILURE,
                            "Api on response failure"
                        )
                    }
                }

                override fun onFailure(call: Call<HotelListData>, t: Throwable) {
                    view.onGetHotelList(
                        HOTEL_LIST_STATE.API_ON_FAILURE,
                        t.message ?: "Api on failure"
                    )
                }
            }
            )
        }
    }
}
