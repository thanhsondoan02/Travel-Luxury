package ai.ftech.travelluxury.hoteldetail

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.OnClickListener
import ai.ftech.travelluxury.data.getPriceString
import ai.ftech.travelluxury.hoteldetail.description.SeeDescriptionActivity
import ai.ftech.travelluxury.hoteldetail.facilities.SeeFacilitiesActivity
import ai.ftech.travelluxury.hoteldetail.policies.SeePoliciesActivity
import ai.ftech.travelluxury.hoteldetail.reviews.SeeReviewsActivity
import ai.ftech.travelluxury.model.hoteldetail.HotelDetail.Companion.HOTEL_DETAIL
import ai.ftech.travelluxury.model.selectroom.SelectRoomModel.Companion.SELECT_ROOM_MODEL
import ai.ftech.travelluxury.selectroom.SelectRoomActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelDetailActivity : AppCompatActivity(), OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HotelDetailAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var btnSelectRoom: Button
    private lateinit var tvPrice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_detail_activity)

        HOTEL_DETAIL.mockData()
        initView()

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

    private fun initView() {
        recyclerView = findViewById(R.id.rvHotelDetailRecyclerView)

        adapter = HotelDetailAdapter(this)
        recyclerView.adapter = adapter

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        tvPrice = findViewById(R.id.tvHotelDetailPrice)
        tvPrice.text = getPriceString(HOTEL_DETAIL.hotel!!.smallestPrice)

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
