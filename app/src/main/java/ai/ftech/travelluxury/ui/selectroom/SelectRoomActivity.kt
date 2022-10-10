package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseActivity
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.model.selectroom.Room
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel
import ai.ftech.travelluxury.ui.hoteldetail.allphotos.photo.ViewPhotoActivity
import ai.ftech.travelluxury.ui.reserve.ReserveActivity
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectRoomActivity : BaseActivity(), SelectRoomContract.IView {

    interface IListener {
        fun onRoomSelected(room: Room)
        fun onImageClick(imageList: List<String>, imageIndex: Int)
        fun onChooseDateClick()
        fun onChooseDurationClick()
    }

    lateinit var adapter: SelectRoomAdapter

    private lateinit var rvRoomList: RecyclerView
    private lateinit var tvHotelName: TextView
    private lateinit var tvHotelAddress: TextView
    private lateinit var btnGoBack: ImageButton
    private lateinit var rlCheckInDate: RelativeLayout
    private lateinit var rlDuration: RelativeLayout
    private lateinit var tvCheckInDate: TextView
    private lateinit var tvDuration: TextView

    private lateinit var listener: IListener
    private val presenter: SelectRoomContract.IPresenter by lazy {
        SelectRoomPresenter().apply {
            view = this@SelectRoomActivity
        }
    }
    private val getContent: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val date = result.data?.getStringExtra("date")
                val duration = SelectRoomModel.INSTANCE.duration
                if (date != null) {
                    SelectRoomModel.INSTANCE.checkInDate = date
                    this@SelectRoomActivity.onCheckInDateChange()
                    if (duration != null) {
                        presenter.getSpecialRoomList()
                        onRoomListChange()
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()

        // set text for hotel name and address
        tvHotelName.text = SelectRoomModel.INSTANCE.hotelName
        tvHotelAddress.text = SelectRoomModel.INSTANCE.hotelAddress

        // on click
        btnGoBack.setOnClickListener { onBackPressed() }
        rlCheckInDate.setOnClickListener { listener.onChooseDateClick() }
        rlDuration.setOnClickListener { listener.onChooseDurationClick() }

        // init recycle view
        rvRoomList.layoutManager = LinearLayoutManager(this)
        adapter = SelectRoomAdapter().apply {
            listener = this@SelectRoomActivity.listener
        }
        rvRoomList.adapter = adapter

        showLoading("", "")
        presenter.getRoomListApi()
    }

    override fun onBackPressed() {
        SelectRoomModel.INSTANCE.reset()
        super.onBackPressed()
    }

    override fun getLayoutId() = R.layout.select_room_activity

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetRoomListSuccess() {
        hideLoading()
        rvRoomList.adapter?.notifyDataSetChanged()
    }

    override fun onGetRoomListFail(message: String) {
        hideLoading()
        Log.d(TAG, "onGetRoomListFail: $message")
    }

    override fun onCheckInDateChange() {
        tvCheckInDate.text = SelectRoomModel.INSTANCE.checkInDate
    }

    @SuppressLint("SetTextI18n")
    override fun onDurationChange() {
        tvDuration.text = SelectRoomModel.INSTANCE.duration.toString() + " night(s)"
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onRoomListChange() {
        showLoading("", "")
        rvRoomList.adapter?.notifyDataSetChanged()
    }

    private fun initView() {
        rvRoomList = findViewById(R.id.rvSelectRoomRecycler)
        tvHotelName = findViewById(R.id.tvSelectRoomHotelName)
        tvHotelAddress = findViewById(R.id.tvSelectRoomHotelAddress)
        btnGoBack = findViewById(R.id.ivSelectRoomBack)
        rlCheckInDate = findViewById(R.id.rlSelectRoomCheckInDate)
        rlDuration = findViewById(R.id.rlSelectRoomDuration)
        tvCheckInDate = findViewById(R.id.tvSelectRoomCheckInDate)
        tvDuration = findViewById(R.id.tvSelectRoomDuration)
    }

    private fun initListener() {
        listener = object : IListener {
            override fun onRoomSelected(room: Room) {
                startActivity(Intent(this@SelectRoomActivity, ReserveActivity::class.java))
            }

            override fun onImageClick(imageList: List<String>, imageIndex: Int) {
                val intent = Intent(this@SelectRoomActivity, ViewPhotoActivity::class.java)
                intent.putExtra("imageList", ArrayList<String>(imageList))
                intent.putExtra("index", imageIndex)
                startActivity(intent)
            }

            override fun onChooseDateClick() {
                val intent = Intent(this@SelectRoomActivity, ChooseDateActivity::class.java)
                getContent.launch(intent)
            }

            /**
             * Show dialog to choose duration
             */
            @SuppressLint("InflateParams")
            override fun onChooseDurationClick() {
                val dialog = Dialog(this@SelectRoomActivity, R.style.DialogTheme)

                val inflateView = layoutInflater.inflate(R.layout.duration_picker_layout, null)

                // number picker config
                val npNumberPicker = inflateView.findViewById<NumberPicker>(R.id.npDurationPicker)
                npNumberPicker.minValue = 1
                npNumberPicker.maxValue = 30
                val durationList = mutableListOf<String>()
                for (i in 1..30) {
                    durationList.add("$i night(s)")
                }
                npNumberPicker.displayedValues = durationList.toTypedArray()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    npNumberPicker.textColor = getColor(R.color.main_blue_color)
                }

                // cancel button
                val btnCancel = inflateView.findViewById<Button>(R.id.btnDurationPickerCancel)
                btnCancel.setOnClickListener { dialog.dismiss() }

                // select button
                val btnSelect = inflateView.findViewById<Button>(R.id.btnDurationPickerSelect)
                btnSelect.setOnClickListener {
                    SelectRoomModel.INSTANCE.duration = npNumberPicker.value
                    this@SelectRoomActivity.onDurationChange()
                    val checkInDate = SelectRoomModel.INSTANCE.checkInDate
                    if (checkInDate != null) {
                        presenter.getSpecialRoomList()
                        onRoomListChange()
                    }
                    dialog.dismiss()
                }

                dialog.setContentView(inflateView)

                dialog.window?.apply {
                    setGravity(Gravity.BOTTOM)
                    setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT
                    )
                }

                dialog.show()
            }
        }
    }
}
