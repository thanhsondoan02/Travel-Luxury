package ai.ftech.travelluxury.ui.hoteldetail.allphotos

import ai.ftech.travelluxury.R
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AllPhotosActivity : AppCompatActivity() {

    private lateinit var ivGoBack: ImageView
    private lateinit var rvAllPhotos: RecyclerView
    private lateinit var ivList: ImageView
    private lateinit var ivGrid: ImageView

    private var isGridView: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_photos_activity)

        initView()

        // init recycle view
        rvAllPhotos.adapter = AllPhotosAdapter()
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
        ivGrid.setOnClickListener { onGridView() }
        ivList.setOnClickListener { onListView() }
    }

    private fun onGridView() {
        if (!isGridView) {
            ivGrid.setImageResource(R.drawable.ic_grid_blue)
            ivList.setImageResource(R.drawable.ic_menu_white)
            rvAllPhotos.layoutManager = GridLayoutManager(this@AllPhotosActivity, 4)
            isGridView = true
        }
    }

    private fun onListView() {
        if (isGridView) {
            ivGrid.setImageResource(R.drawable.ic_grid_white)
            ivList.setImageResource(R.drawable.ic_menu_blue)
            rvAllPhotos.layoutManager = LinearLayoutManager(this@AllPhotosActivity)
            isGridView = false
        }
    }
}
