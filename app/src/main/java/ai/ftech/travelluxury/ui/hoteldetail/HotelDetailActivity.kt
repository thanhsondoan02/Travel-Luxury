package ai.ftech.travelluxury.ui.hoteldetail

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.OnClickListener
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.getPriceString
import ai.ftech.travelluxury.ui.hoteldetail.description.SeeDescriptionActivity
import ai.ftech.travelluxury.ui.hoteldetail.facilities.SeeFacilitiesActivity
import ai.ftech.travelluxury.ui.hoteldetail.policies.SeePoliciesActivity
import ai.ftech.travelluxury.ui.hoteldetail.reviews.SeeReviewsActivity
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailModel.Companion.HOTEL_DETAIL_MODEL
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel.Companion.SELECT_ROOM_MODEL
import ai.ftech.travelluxury.ui.selectroom.SelectRoomActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelDetailActivity : AppCompatActivity(), OnClickListener, HotelDetailContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HotelDetailAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var btnSelectRoom: Button
    private lateinit var tvPrice: TextView

    private lateinit var presenter: HotelDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_detail_activity)

//        HOTEL_DETAIL_MODEL.mockData()
        initView()

        presenter = HotelDetailPresenter(this, adapter)
        presenter.getHotelDetailApi()
    }

    override fun onClick(nextActivity: NEXT_ACTIVITY) {
        when (nextActivity) {
            NEXT_ACTIVITY.SEE_REVIEWS -> {
                startActivity(Intent(this, SeeReviewsActivity::class.java))
            }
            NEXT_ACTIVITY.SEE_FACILITIES -> {
                startActivity(Intent(this, SeeFacilitiesActivity::class.java))
            }
            NEXT_ACTIVITY.SEE_POLICIES -> {
                startActivity(Intent(this, SeePoliciesActivity::class.java))
            }
            NEXT_ACTIVITY.SEE_DESCRIPTION -> {
                startActivity(Intent(this, SeeDescriptionActivity::class.java))
            }
            NEXT_ACTIVITY.SELECT_ROOM -> {
                startActivity(Intent(this, SelectRoomActivity::class.java))
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetHotelDetail(state: HOTEL_DETAIL_STATE, message: String) {
        Log.d(TAG, "onGetHotelDetail: $message")
        when (state) {
            HOTEL_DETAIL_STATE.SUCCESS -> {
                adapter.notifyDataSetChanged()
                adapter.facilitiesAdapter.onNewData(adapter.facilitiesList)
            }
            HOTEL_DETAIL_STATE.NULL_HOTEL_ID, HOTEL_DETAIL_STATE.API_ON_FAILURE, HOTEL_DETAIL_STATE.API_ON_RESPONSE_FAILURE -> {
            }
        }
    }

    private fun initView() {
        recyclerView = findViewById(R.id.rvHotelDetailRecyclerView)

        adapter = HotelDetailAdapter(this)
        recyclerView.adapter = adapter

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        tvPrice = findViewById(R.id.tvHotelDetailPrice)
        tvPrice.text = getPriceString(HOTEL_DETAIL_MODEL.hotelInfo!!.smallestPrice)

        btnSelectRoom = findViewById(R.id.btnHotelDetailSelectRoom)
        btnSelectRoom.setOnClickListener {
            SELECT_ROOM_MODEL.fromHotelDetail()
            onClick(NEXT_ACTIVITY.SELECT_ROOM)
        }
    }

    enum class NEXT_ACTIVITY {
        SEE_REVIEWS, SEE_FACILITIES, SEE_POLICIES, SEE_DESCRIPTION, SELECT_ROOM
    }

}
