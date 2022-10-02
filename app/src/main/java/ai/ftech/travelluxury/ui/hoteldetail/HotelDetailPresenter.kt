package ai.ftech.travelluxury.ui.hoteldetail

import ai.ftech.travelluxury.data.source.api.APIService
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailData
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailModel.Companion.HOTEL_DETAIL_MODEL
import ai.ftech.travelluxury.data.model.hoteldetail.HotelInfo
import ai.ftech.travelluxury.data.model.hoteldetail.Rating
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelDetailPresenter(
    private val view: HotelDetailContract.View,
    private val adapter: HotelDetailAdapter
) : HotelDetailContract.Presenter {
    override fun getHotelDetailApi() {
        val hotelId = HOTEL_DETAIL_MODEL.hotelInfo?.id

        if (hotelId == null) {
            view.onGetHotelDetail(HOTEL_DETAIL_STATE.NULL_HOTEL_ID, "Hotel is null")
        } else {

            APIService.base().getHotelDetail().enqueue(object : Callback<HotelDetailData> {

                override fun onResponse(
                    call: Call<HotelDetailData>,
                    response: Response<HotelDetailData>
                ) {
                    val hotelDetailModel = response.body()?.data

                    if (hotelDetailModel != null) {

                        // update data in HOTEL_DETAIL_MODEL
                        HOTEL_DETAIL_MODEL.imageList = hotelDetailModel.imageList
                        HOTEL_DETAIL_MODEL.hotelInfo = hotelDetailModel.hotelInfo
                        HOTEL_DETAIL_MODEL.rating = hotelDetailModel.rating
                        HOTEL_DETAIL_MODEL.facilitiesList = hotelDetailModel.facilitiesList
                        HOTEL_DETAIL_MODEL.policyList = hotelDetailModel.policyList
                        HOTEL_DETAIL_MODEL.descriptionList = hotelDetailModel.descriptionList

                        // update data in adapter
                        adapter.imageUrlList = HOTEL_DETAIL_MODEL.imageList ?: mutableListOf()
                        adapter.hotelInfo = HOTEL_DETAIL_MODEL.hotelInfo ?: HotelInfo()
                        adapter.rating = HOTEL_DETAIL_MODEL.rating ?: Rating()
                        adapter.policyList = HOTEL_DETAIL_MODEL.policyList ?: listOf()
                        adapter.facilitiesList = HOTEL_DETAIL_MODEL.facilitiesList ?: listOf()

                        view.onGetHotelDetail(HOTEL_DETAIL_STATE.SUCCESS, "Api on success")
                    } else {
                        view.onGetHotelDetail(
                            HOTEL_DETAIL_STATE.API_ON_RESPONSE_FAILURE,
                            "Api on response failure"
                        )
                    }
                }

                override fun onFailure(call: Call<HotelDetailData>, t: Throwable) {
                    view.onGetHotelDetail(
                        HOTEL_DETAIL_STATE.API_ON_FAILURE,
                        t.message ?: "Api on failure"
                    )
                }

            })
        }
    }
}
