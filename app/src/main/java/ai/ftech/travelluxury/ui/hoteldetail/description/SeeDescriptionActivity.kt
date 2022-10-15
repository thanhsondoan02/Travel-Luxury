package ai.ftech.travelluxury.ui.hoteldetail.description

import ai.ftech.travelluxury.R
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SeeDescriptionActivity : AppCompatActivity() {

    private lateinit var btnGoBack: ImageButton
    private lateinit var tvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.see_description_activity)
        initView()
        btnGoBack.setOnClickListener { onBackPressed() }
    }

    private fun initView() {
        btnGoBack = findViewById(R.id.btnActionBarGoBack)
        tvTitle = findViewById(R.id.tvActionBarTitle)

        // init text
        tvTitle.text = getString(R.string.description)
    }
}
