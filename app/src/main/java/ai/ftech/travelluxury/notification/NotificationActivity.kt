package ai.ftech.travelluxury.notification

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.view.ActionBarView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NotificationActivity : AppCompatActivity() {

    private lateinit var actionbar: ActionBarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_activity)

        initView()
    }

    private fun initView() {
        actionbar = findViewById(R.id.abvNotificationActionBar)

        actionbar.setTitle("Notification")
    }
}