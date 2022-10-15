package ai.ftech.travelluxury.ui.search

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.customview.ActionBarView
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var actionBar: ActionBarView
    private lateinit var btnGoBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        initView()

        btnGoBack.setOnClickListener { onBackPressed() }
    }

    private fun initView() {
        actionBar = findViewById(R.id.abvSearchActionBar)
        btnGoBack = findViewById(R.id.btnActionBarGoBack)

        actionBar.setTitle("Search")
    }
}
