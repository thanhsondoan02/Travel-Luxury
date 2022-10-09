package ai.ftech.travelluxury.ui.hoteldetail

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseActivity
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.getPriceString
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailModel
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel
import ai.ftech.travelluxury.ui.hoteldetail.allphotos.AllPhotosActivity
import ai.ftech.travelluxury.ui.hoteldetail.allphotos.photo.ViewPhotoActivity
import ai.ftech.travelluxury.ui.hoteldetail.description.SeeDescriptionActivity
import ai.ftech.travelluxury.ui.hoteldetail.facilities.SeeFacilitiesActivity
import ai.ftech.travelluxury.ui.hoteldetail.policies.SeePoliciesActivity
import ai.ftech.travelluxury.ui.hoteldetail.reviews.SeeReviewsActivity
import ai.ftech.travelluxury.ui.selectroom.SelectRoomActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelDetailActivity : BaseActivity(), HotelDetailContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSelectRoom: Button
    private lateinit var tvPrice: TextView
    private lateinit var rlLoading: LinearLayout
    private lateinit var btnGoBack: ImageButton

    private lateinit var listener: IListener
    private lateinit var adapter: HotelDetailAdapter
    private lateinit var presenter: HotelDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()
        initRecyclerView()
        initPresenter()

        // set hotel smallest price
        tvPrice.text = getPriceString(HotelDetailModel.INSTANCE.hotelInfo!!.smallestPrice)

        // on select room clicked
        btnSelectRoom.setOnClickListener {
            SelectRoomModel.INSTANCE.fromHotelDetail()
            listener.onSelectRoom()
        }

        // on go back clicked
        btnGoBack.setOnClickListener {
            onBackPressed()
        }

        showLoading("", "")
        presenter.getHotelDetailApi()
    }

    override fun getLayoutId(): Int {
        return R.layout.hotel_detail_activity
    }

    override fun hideLoading() {
        super.hideLoading()
        rlLoading.visibility = View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetHotelDetailSuccess() {
        hideLoading()
        adapter.notifyDataSetChanged()
        adapter.facilitiesAdapter.notifyDataSetChanged()
    }

    override fun onGetHotelDetailFail(message: String) {
        hideLoading()
        Log.d(TAG, "onGetHotelDetail: $message")
    }

    private fun initView() {
        recyclerView = findViewById(R.id.rvHotelDetailRecyclerView)
        tvPrice = findViewById(R.id.tvHotelDetailPrice)
        btnSelectRoom = findViewById(R.id.btnHotelDetailSelectRoom)
        rlLoading = findViewById(R.id.rlHotelDetailLoading)
        btnGoBack = findViewById(R.id.btnHotelDetailBack)
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

            override fun onViewPhoto(index: Int) {
                val intent = Intent(this@HotelDetailActivity, ViewPhotoActivity::class.java)
                intent.putExtra("index", index)
                startActivity(intent)
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
