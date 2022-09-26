package ai.ftech.travelluxury.hotellist

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.hoteldetail.HotelDetailActivity
import ai.ftech.travelluxury.model.hotellist.HotelListModel.Companion.HOTEL_LIST_MODEL
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelListActivity : AppCompatActivity() {

    private lateinit var tvCityName: TextView
    private lateinit var rvHotelList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_list_activity)

        HOTEL_LIST_MODEL.mockData()

        initViews()

    }

    private fun initViews() {
        tvCityName = findViewById(R.id.tvHotelListCityName)
        rvHotelList = findViewById(R.id.rvHotelList)

        // init action bar
        tvCityName.text = HOTEL_LIST_MODEL.cityName

        // init recyclerview
        rvHotelList.layoutManager = LinearLayoutManager(this)
        rvHotelList.adapter = HotelListAdapter().apply {
            listener = object : HotelListAdapter.Listener {
                override fun onHotelClick() {
                    startActivity(Intent(this@HotelListActivity, HotelDetailActivity::class.java))
                }
            }
        }
    }

}
