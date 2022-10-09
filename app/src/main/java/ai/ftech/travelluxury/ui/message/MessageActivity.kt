package ai.ftech.travelluxury.ui.message

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.customview.ActionBarView
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MessageActivity : AppCompatActivity() {

    private lateinit var actionBar: ActionBarView
    private lateinit var btnGoBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_activity)

        initView()

        btnGoBack.setOnClickListener { onBackPressed() }
    }

    private fun initView() {
        actionBar = findViewById(R.id.abvMessageActionBar)
        btnGoBack = actionBar.findViewById(R.id.btnActionBarGoBack)

        // init text
        actionBar.setTitle("Message")
    }
}
