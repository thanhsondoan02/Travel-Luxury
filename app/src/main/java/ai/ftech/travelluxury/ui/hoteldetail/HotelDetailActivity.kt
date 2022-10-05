package ai.ftech.travelluxury.ui.hoteldetail

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.getPriceString
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailModel.Companion.INSTANCE
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel.Companion.SELECT_ROOM_MODEL
import ai.ftech.travelluxury.ui.hoteldetail.allphotos.AllPhotosActivity
import ai.ftech.travelluxury.ui.hoteldetail.description.SeeDescriptionActivity
import ai.ftech.travelluxury.ui.hoteldetail.facilities.SeeFacilitiesActivity
import ai.ftech.travelluxury.ui.hoteldetail.policies.SeePoliciesActivity
import ai.ftech.travelluxury.ui.hoteldetail.reviews.SeeReviewsActivity
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

class HotelDetailActivity : AppCompatActivity(), HotelDetailContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSelectRoom: Button
    private lateinit var tvPrice: TextView

    private lateinit var listener: IListener
    private lateinit var adapter: HotelDetailAdapter
    private lateinit var presenter: HotelDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_detail_activity)

        initView()
        initListener()
        initRecyclerView()
        initPresenter()

        // set hotel smallest price
        tvPrice.text = getPriceString(INSTANCE.hotelInfo!!.smallestPrice)

        // on select room clicked
        btnSelectRoom.setOnClickListener {
            SELECT_ROOM_MODEL.fromHotelDetail()
            listener.onSelectRoom()
        }

        presenter.getHotelDetailApi()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetHotelDetailSuccess() {
        adapter.notifyDataSetChanged()
        adapter.facilitiesAdapter.notifyDataSetChanged()
    }

    override fun onGetHotelDetailFail(message: String) {
        Log.d(TAG, "onGetHotelDetail: $message")
    }

    private fun initView() {
        recyclerView = findViewById(R.id.rvHotelDetailRecyclerView)
        tvPrice = findViewById(R.id.tvHotelDetailPrice)
        btnSelectRoom = findViewById(R.id.btnHotelDetailSelectRoom)
    }

    private fun initListener() {
        listener = object : IListener {
            override fun onSeeAllPhotos() {
                startActivity(Intent(this@HotelDetailActivity, AllPhotosActivity::class.java))
            }

            override fun onSeeAllReviews() {
                startActivity(Intent(this@HotelDetailActivity, SeeReviewsActivity::class.java))
            }

            override fun onSeeAllFacilities() {
                startActivity(Intent(this@HotelDetailActivity, SeeFacilitiesActivity::class.java))
            }

            override fun onSeeAllPolicies() {
                startActivity(Intent(this@HotelDetailActivity, SeePoliciesActivity::class.java))
            }

            override fun onSeeAllDescription() {
                startActivity(Intent(this@HotelDetailActivity, SeeDescriptionActivity::class.java))
            }

            override fun onSelectRoom() {
                startActivity(Intent(this@HotelDetailActivity, SelectRoomActivity::class.java))
            }
        }
    }

    private fun initRecyclerView() {
        adapter = HotelDetailAdapter().apply {
            listener = this@HotelDetailActivity.listener
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initPresenter() {
        presenter = HotelDetailPresenter().apply {
            view = this@HotelDetailActivity
            adapter = this@HotelDetailActivity.adapter
        }
    }

}
