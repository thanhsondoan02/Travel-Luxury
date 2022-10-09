package ai.ftech.travelluxury.ui.notification

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.customview.ActionBarView
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {

    private lateinit var actionbar: ActionBarView
    private lateinit var btnGoBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_activity)

        initView()

        btnGoBack.setOnClickListener { onBackPressed() }
    }

    private fun initView() {
        actionbar = findViewById(R.id.abvNotificationActionBar)
        btnGoBack = findViewById(R.id.btnActionBarGoBack)

        actionbar.setTitle("Notification")
    }
}
