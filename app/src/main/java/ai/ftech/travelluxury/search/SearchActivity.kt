package ai.ftech.travelluxury.search

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.view.ActionBarView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SearchActivity : AppCompatActivity() {

    private lateinit var actionBar: ActionBarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        initView()

    }

    private fun initView() {
        actionBar = findViewById(R.id.abvSearchActionBar)

        actionBar.setTitle("Search")
    }
}