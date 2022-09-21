package ai.ftech.travelluxury.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.model.selectroom.SelectRoomModel.Companion.SELECT_ROOM_MODEL
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectRoomActivity : AppCompatActivity() {

    private lateinit var rvRoomList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_room_activity)

        SELECT_ROOM_MODEL.mockData()
        initView()
    }

    private fun initView() {

        rvRoomList = findViewById(R.id.rvSelectRoomRecycler)
        rvRoomList.layoutManager = LinearLayoutManager(this)
        rvRoomList.adapter = SelectRoomAdapter()
    }
}
