package ai.ftech.travelluxury.ui.hoteldetail.allphotos

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.hoteldetail.allphotos.photo.ViewPhotoActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AllPhotosActivity : AppCompatActivity() {

    interface IListener {
        fun onPhotoClick(index: Int)
        fun onGridView()
        fun onListView()
    }

    private lateinit var ivGoBack: ImageView
    private lateinit var rvAllPhotos: RecyclerView
    private lateinit var ivList: ImageView
    private lateinit var ivGrid: ImageView

    private val listener = object : IListener {

        override fun onPhotoClick(index: Int) {
            val intent = Intent(this@AllPhotosActivity, ViewPhotoActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }

        override fun onGridView() {
            if (!isGridView) {
                ivGrid.setImageResource(R.drawable.ic_grid_blue)
                ivList.setImageResource(R.drawable.ic_menu_white)
                rvAllPhotos.layoutManager = GridLayoutManager(this@AllPhotosActivity, 4)
                isGridView = true
            }
        }

        override fun onListView() {
            if (isGridView) {
                ivGrid.setImageResource(R.drawable.ic_grid_white)
                ivList.setImageResource(R.drawable.ic_menu_blue)
                rvAllPhotos.layoutManager = LinearLayoutManager(this@AllPhotosActivity)
                isGridView = false
            }
        }

    }

    private var isGridView: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_photos_activity)

        initView()

        // init recycle view
        rvAllPhotos.adapter = AllPhotosAdapter().apply {
            listener = this@AllPhotosActivity.listener
        }
        rvAllPhotos.layoutManager = GridLayoutManager(this, 4)

        setOnClick()
    }

    private fun initView() {
        ivGoBack = findViewById(R.id.ivAllPhotosBack)
        rvAllPhotos = findViewById(R.id.rvAllPhotos)
        ivGrid = findViewById(R.id.ivAllPhotosGrid)
        ivList = findViewById(R.id.ivAllPhotosList)
    }

    private fun setOnClick() {
        ivGoBack.setOnClickListener { onBackPressed() }
        ivGrid.setOnClickListener { listener.onGridView() }
        ivList.setOnClickListener { listener.onListView() }
    }
}
