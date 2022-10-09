package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseActivity
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.model.selectroom.Room
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel
import ai.ftech.travelluxury.ui.hoteldetail.allphotos.photo.ViewPhotoActivity
import ai.ftech.travelluxury.ui.reserve.ReserveActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectRoomActivity : BaseActivity(), SelectRoomContract.IView {

    interface IListener {
        fun onRoomSelected(room: Room)
        fun onImageClick(imageList: List<String>, imageIndex: Int)
    }

    lateinit var adapter: SelectRoomAdapter

    private lateinit var rvRoomList: RecyclerView
    private lateinit var tvHotelName: TextView
    private lateinit var tvHotelAddress: TextView
    private lateinit var btnGoBack: ImageButton

    private lateinit var listener: IListener
    private val presenter: SelectRoomContract.IPresenter by lazy {
        SelectRoomPresenter().apply {
            view = this@SelectRoomActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()

        // set text for hotel name and address
        tvHotelName.text = SelectRoomModel.INSTANCE.hotelName
        tvHotelAddress.text = SelectRoomModel.INSTANCE.hotelAddress

        // on go back click
        btnGoBack.setOnClickListener { onBackPressed() }

        // init recycle view
        rvRoomList.layoutManager = LinearLayoutManager(this)
        adapter = SelectRoomAdapter().apply {
            listener = this@SelectRoomActivity.listener
        }
        rvRoomList.adapter = adapter

        showLoading("", "")
        presenter.getRoomListApi()
    }

    override fun getLayoutId() = R.layout.select_room_activity

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetRoomListSuccess() {
        hideLoading()
        rvRoomList.adapter?.notifyDataSetChanged()
    }

    override fun onGetRoomListFail(message: String) {
        Log.d(TAG, "onGetRoomListFail: $message")
    }

    private fun initView() {
        rvRoomList = findViewById(R.id.rvSelectRoomRecycler)
        tvHotelName = findViewById(R.id.tvSelectRoomHotelName)
        tvHotelAddress = findViewById(R.id.tvSelectRoomHotelAddress)
        btnGoBack = findViewById(R.id.ivSelectRoomBack)
    }

    private fun initListener() {
        listener = object : IListener {
            override fun onRoomSelected(room: Room) {
                startActivity(Intent(this@SelectRoomActivity, ReserveActivity::class.java))
            }

            override fun onImageClick(imageList: List<String>, imageIndex: Int) {
                val intent = Intent(this@SelectRoomActivity, ViewPhotoActivity::class.java)
//                intent.putExtra("imageList", imageList as ArrayList<String>)
                intent.putExtra("imageList", ArrayList<String>(imageList))
                intent.putExtra("index", imageIndex)
                startActivity(intent)
            }
        }
    }
}
