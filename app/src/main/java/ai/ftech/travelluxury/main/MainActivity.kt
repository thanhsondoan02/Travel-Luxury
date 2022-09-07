package ai.ftech.travelluxury.main

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.main.fragment.*
import ai.ftech.travelluxury.message.MessageActivity
import ai.ftech.travelluxury.notification.NotificationActivity
import ai.ftech.travelluxury.search.SearchActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var rlSearchBox: RelativeLayout
    private lateinit var ivNotification: ImageView
    private lateinit var ivMessage: ImageView
    private lateinit var flContent: FrameLayout
    private lateinit var bnvNavigator: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initView()
        setOnCLickListener()

        loadFragment(HomeFragment())

        setNavigatorListener()
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
        flContent = findViewById(R.id.flMainContent)
        bnvNavigator = findViewById(R.id.bnvMainNavigator)
    }

    private fun setOnCLickListener() {
        rlSearchBox.setOnClickListener(this)
        ivNotification.setOnClickListener(this)
        ivMessage.setOnClickListener(this)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainContent, fragment)
            .commit()
    }

    private fun setNavigatorListener() {
        bnvNavigator.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itmBottomNavigationHome -> {
                    loadFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationExplore -> {
                    loadFragment(ExploreFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationMyBooking -> {
                    loadFragment(MyBookingFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationSaved -> {
                    loadFragment(SavedFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationMyAccount -> {
                    loadFragment(MyAccountFragment())
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }

}
