package ai.ftech.travelluxury.ui.hoteldetail.allphotos.photo

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.selectroom.RoomImageAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class ViewPhotoActivity : AppCompatActivity() {

//    var imageList: List<String> = HotelDetailModel.INSTANCE.imageList ?: listOf()

    private lateinit var vpViewPager: ViewPager
    private lateinit var btnClose: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_photo_activity)

        initView()

        btnClose.setOnClickListener { onBackPressed() }

        vpViewPager.adapter = RoomImageAdapter().apply {
            imageList =
                this@ViewPhotoActivity.intent.getStringArrayListExtra("imageList") ?: listOf()
            scaleType = ImageView.ScaleType.FIT_CENTER
        }
        vpViewPager.currentItem = intent.getIntExtra("index", 0)
    }

    private fun initView() {
        vpViewPager = findViewById(R.id.vpViewPhoto)
        btnClose = findViewById(R.id.tvViewPhotoClose)
    }

}
