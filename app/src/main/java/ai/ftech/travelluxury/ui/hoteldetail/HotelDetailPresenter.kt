package ai.ftech.travelluxury.ui.hoteldetail

import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailModel
import ai.ftech.travelluxury.data.model.hoteldetail.HotelInfo
import ai.ftech.travelluxury.data.model.hoteldetail.Rating
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl

import ai.ftech.travelluxury.data.repo.hotel.IResult
import android.annotation.SuppressLint

class HotelDetailPresenter : HotelDetailContract.Presenter {

    var view: HotelDetailContract.View? = null
    var adapter: HotelDetailAdapter? = null

    private val hotelRepo by lazy {
        HotelRepositoryImpl().apply {

            result = object : IResult {

                @SuppressLint("NotifyDataSetChanged")
                override fun onRepoSuccess(data: Any) {
                    val hotelDetailModel = data as HotelDetailModel

                    // update data in HOTEL_DETAIL_MODEL
                    HotelDetailModel.INSTANCE.imageList = hotelDetailModel.imageList
                    HotelDetailModel.INSTANCE.hotelInfo = hotelDetailModel.hotelInfo
                    HotelDetailModel.INSTANCE.rating = hotelDetailModel.rating
                    HotelDetailModel.INSTANCE.facilitiesList = hotelDetailModel.facilitiesList
                    HotelDetailModel.INSTANCE.policyList = hotelDetailModel.policyList
                    HotelDetailModel.INSTANCE.descriptionList = hotelDetailModel.descriptionList

                    // update data in adapter
                    adapter?.imageUrlList = HotelDetailModel.INSTANCE.imageList ?: mutableListOf()
                    adapter?.hotelInfo = HotelDetailModel.INSTANCE.hotelInfo ?: HotelInfo()
                    adapter?.rating = HotelDetailModel.INSTANCE.rating ?: Rating()
                    adapter?.policyList = HotelDetailModel.INSTANCE.policyList ?: listOf()
                    adapter?.facilitiesAdapter?.dataList =
                        HotelDetailModel.INSTANCE.facilitiesList ?: listOf()

                    view?.onGetHotelDetailSuccess()
                }

                override fun onRepoFail(message: String) {
                    view?.onGetHotelDetailFail(message)
                }
            }
        }
    }

    override fun getHotelDetailApi() {
        val hotelId = HotelDetailModel.INSTANCE.hotelInfo?.id

        if (hotelId == null) {
            view?.onGetHotelDetailFail("Hotel is null")
        } else {
            hotelRepo.getHotelDetail(hotelId)
        }
    }
}
