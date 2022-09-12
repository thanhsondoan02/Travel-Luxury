package ai.ftech.travelluxury.main

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.main.fragment.*
import ai.ftech.travelluxury.main.myaccount.MyAccountFragment
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
    private lateinit var ivNotification: RelativeLayout
    private lateinit var ivMessage: RelativeLayout
    private lateinit var flContent: FrameLayout
    private lateinit var bnvNavigator: BottomNavigationView

    private lateinit var homeFragment: HomeFragment
    private lateinit var exploreFragment: ExploreFragment
    private lateinit var myBookingFragment: MyBookingFragment
    private lateinit var savedFragment: SavedFragment
    private lateinit var myAccountFragment: MyAccountFragment

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
            R.id.rlMainNotification -> startActivity(Intent(this, NotificationActivity::class.java))
            R.id.rlMainMessage -> startActivity(Intent(this, MessageActivity::class.java))
        }
    }

    private fun initView() {
        rlSearchBox = findViewById(R.id.rlMainSearchBox)
        ivNotification = findViewById(R.id.rlMainNotification)
        ivMessage = findViewById(R.id.rlMainMessage)
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
                    if (!this::homeFragment.isInitialized) homeFragment = HomeFragment()
                    loadFragment(homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationExplore -> {
                    if (!this::exploreFragment.isInitialized) exploreFragment = ExploreFragment()
                    loadFragment(exploreFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationMyBooking -> {
                    if (!this::myBookingFragment.isInitialized) myBookingFragment = MyBookingFragment()
                    loadFragment(myBookingFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationSaved -> {
                    if (!this::savedFragment.isInitialized) savedFragment = SavedFragment()
                    loadFragment(savedFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationMyAccount -> {
                    if (!this::myAccountFragment.isInitialized) myAccountFragment = MyAccountFragment()
                    loadFragment(myAccountFragment)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }

}
