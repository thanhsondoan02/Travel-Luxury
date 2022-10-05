package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.model.selectroom.Room
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel.Companion.SELECT_ROOM_MODEL
import ai.ftech.travelluxury.ui.reserve.ReserveActivity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectRoomActivity : AppCompatActivity() {

    private lateinit var rvRoomList: RecyclerView
    private lateinit var tvHotelName: TextView
    private lateinit var tvHotelAddress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_room_activity)

        SELECT_ROOM_MODEL.mockData()
        initView()

        // set text for hotel name and address
        tvHotelName.text = SELECT_ROOM_MODEL.hotelName
        tvHotelAddress.text = SELECT_ROOM_MODEL.hotelAddress

        // init recycle view
        rvRoomList.layoutManager = LinearLayoutManager(this)
        rvRoomList.adapter = SelectRoomAdapter().apply {
            listener = object : SelectRoomAdapter.IListener {
                override fun onRoomSelected(room: Room) {
                    startActivity(Intent(this@SelectRoomActivity, ReserveActivity::class.java))
                }
            }
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()

    }

    private fun initView() {
        rvRoomList = findViewById(R.id.rvSelectRoomRecycler)
        tvHotelName = findViewById(R.id.tvSelectRoomHotelName)
        tvHotelAddress = findViewById(R.id.tvSelectRoomHotelAddress)
    }
}
