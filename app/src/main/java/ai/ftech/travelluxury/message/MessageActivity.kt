package ai.ftech.travelluxury.message

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.view.ActionBarView
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
        actionBar = findViewById(R.id.action_bar)

        // init text
        actionBar.setTitle("Message")
    }
}