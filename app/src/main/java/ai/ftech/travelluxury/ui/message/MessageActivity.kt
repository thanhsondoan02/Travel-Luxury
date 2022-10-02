package ai.ftech.travelluxury.ui.message

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.customview.ActionBarView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MessageActivity : AppCompatActivity() {

    private lateinit var actionBar: ActionBarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_activity)

        initView()
    }

    private fun initView() {
        actionBar = findViewById(R.id.abvMessageActionBar)

        // init text
        actionBar.setTitle("Message")
    }
}
