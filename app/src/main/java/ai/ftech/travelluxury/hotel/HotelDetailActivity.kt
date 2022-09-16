package ai.ftech.travelluxury.hotel

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.model.HotelDetail.Companion.HOTEL_DETAIL
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelDetailActivity : AppCompatActivity(), HotelDetailAdapter.OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HotelDetailAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_detail_activity)

        initView()
        HOTEL_DETAIL.mockData()
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
        }
    }

    private fun initView() {

        recyclerView = findViewById(R.id.rvHotelDetailRecyclerView)

        adapter = HotelDetailAdapter(this)
        recyclerView.adapter = adapter

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

    }

    enum class NEXT_ACTIVITY {
        SEE_REVIEWS, SEE_FACILITIES, SEE_POLICIES, SEE_DESCRIPTION
    }

}
