package ai.ftech.travelluxury.ui.hotellist

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.model.hotellist.HotelListModel
import ai.ftech.travelluxury.ui.hoteldetail.HotelDetailActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelListActivity : AppCompatActivity(), IHotelListContract.View {

    private lateinit var tvCityName: TextView
    private lateinit var rvHotelList: RecyclerView

    private lateinit var adapter: HotelListAdapter
    private val presenter by lazy {
        HotelListPresenter().apply {
            adapter = this@HotelListActivity.adapter
            view = this@HotelListActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_list_activity)

        initViews()
        presenter.getHotelListApi()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetHotelListSuccess() {
        adapter.notifyDataSetChanged()
    }

    override fun onGetHotelListFail(message: String?) {
        Log.d(TAG, "onGetHotelListFail: $message")
    }

    private fun initViews() {
        tvCityName = findViewById(R.id.tvHotelListCityName)
        rvHotelList = findViewById(R.id.rvHotelList)

        // init action bar
        tvCityName.text = HotelListModel.INSTANCE.cityName

        // init recyclerview
        rvHotelList.layoutManager = LinearLayoutManager(this)

        adapter = HotelListAdapter().apply {
            listener = object : HotelListAdapter.Listener {
                override fun onHotelClick() {
                    startActivity(Intent(this@HotelListActivity, HotelDetailActivity::class.java))
                }
            }
        }
        rvHotelList.adapter = adapter
    }
}
