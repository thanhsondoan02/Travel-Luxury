package ai.ftech.travelluxury.main

import ai.ftech.travelluxury.view.ActionBarView
import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.message.MessageActivity
import ai.ftech.travelluxury.notification.NotificationActivity
import ai.ftech.travelluxury.search.SearchActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var searchBar: RelativeLayout
    private lateinit var notificationIcon: ImageView
    private lateinit var messageIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initView()

        searchBar.setOnClickListener(this)
        notificationIcon.setOnClickListener(this)
        messageIcon.setOnClickListener(this)
    }

    private fun initView() {
        searchBar = findViewById(R.id.search_bar)
        notificationIcon = findViewById(R.id.notification_icon)
        messageIcon = findViewById(R.id.message_icon)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.search_bar -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.notification_icon -> startActivity(Intent(this, NotificationActivity::class.java))
            R.id.message_icon -> startActivity(Intent(this, MessageActivity::class.java))
        }
    }

}
