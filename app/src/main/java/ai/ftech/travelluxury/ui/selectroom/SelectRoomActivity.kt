package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseActivity
import ai.ftech.travelluxury.data.model.selectroom.Room
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel.Companion.SELECT_ROOM_MODEL
import ai.ftech.travelluxury.ui.reserve.ReserveActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectRoomActivity : BaseActivity() {

    private lateinit var rvRoomList: RecyclerView
    private lateinit var tvHotelName: TextView
    private lateinit var tvHotelAddress: TextView
    private lateinit var btnGoBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SELECT_ROOM_MODEL.mockData()
        initView()

        // set text for hotel name and address
        tvHotelName.text = SELECT_ROOM_MODEL.hotelName
        tvHotelAddress.text = SELECT_ROOM_MODEL.hotelAddress

        // on go back click
        btnGoBack.setOnClickListener { onBackPressed() }

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

    override fun getLayoutId() = R.layout.select_room_activity

    private fun initView() {
        rvRoomList = findViewById(R.id.rvSelectRoomRecycler)
        tvHotelName = findViewById(R.id.tvSelectRoomHotelName)
        tvHotelAddress = findViewById(R.id.tvSelectRoomHotelAddress)
        btnGoBack = findViewById(R.id.ivSelectRoomBack)
    }
}
