package ai.ftech.travelluxury.ui.hotellist

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.hoteldetail.HotelDetailActivity
import ai.ftech.travelluxury.data.model.hotellist.HotelListModel.Companion.INSTANCE
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

        initViews()
    }

    private fun initViews() {
        tvCityName = findViewById(R.id.tvHotelListCityName)
        rvHotelList = findViewById(R.id.rvHotelList)

        // init action bar
        tvCityName.text = INSTANCE.cityName

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
