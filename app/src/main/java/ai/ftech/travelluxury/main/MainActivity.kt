package ai.ftech.travelluxury.main

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

    private lateinit var rlSearchBox: RelativeLayout
    private lateinit var ivNotification: ImageView
    private lateinit var ivMessage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initView()
        setOnCLickListener()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.rlMainSearchBox -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.ivMainNotification -> startActivity(Intent(this, NotificationActivity::class.java))
            R.id.ivMainMessage -> startActivity(Intent(this, MessageActivity::class.java))
        }
    }

    private fun initView() {
        rlSearchBox = findViewById(R.id.rlMainSearchBox)
        ivNotification = findViewById(R.id.ivMainNotification)
        ivMessage = findViewById(R.id.ivMainMessage)

    }

    private fun setOnCLickListener() {
        rlSearchBox.setOnClickListener(this)
        ivNotification.setOnClickListener(this)
        ivMessage.setOnClickListener(this)
    }

}
