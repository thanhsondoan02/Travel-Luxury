package ai.ftech.travelluxury.ui.main

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.main.explore.ExploreFragment
import ai.ftech.travelluxury.ui.main.home.HomeFragment
import ai.ftech.travelluxury.ui.main.myaccount.MyAccountFragment
import ai.ftech.travelluxury.ui.main.mybooking.MyBookingFragment
import ai.ftech.travelluxury.ui.main.saved.SavedFragment
import ai.ftech.travelluxury.ui.message.MessageActivity
import ai.ftech.travelluxury.ui.notification.NotificationActivity
import ai.ftech.travelluxury.ui.search.SearchActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var rlSearchBox: RelativeLayout
    private lateinit var ivNotification: RelativeLayout
    private lateinit var ivMessage: RelativeLayout
    private lateinit var flContent: FrameLayout
    private lateinit var bnvNavigator: BottomNavigationView

    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val exploreFragment: ExploreFragment by lazy { ExploreFragment() }
    private val myBookingFragment: MyBookingFragment by lazy { MyBookingFragment() }
    private val savedFragment: SavedFragment by lazy { SavedFragment() }
    private val myAccountFragment: MyAccountFragment by lazy { MyAccountFragment() }

    private var isHomeFragment = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initView()
        setOnCLickListener()

        loadFragment(homeFragment)

        setNavigatorListener()
    }

    override fun onBackPressed() {

        if (isHomeFragment) {
            // go to home screen
            val startMain = Intent(Intent.ACTION_MAIN)
            startMain.addCategory(Intent.CATEGORY_HOME)
            startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(startMain)
        } else {
            // go to home fragment
            bnvNavigator.selectedItemId = R.id.itmBottomNavigationHome
        }
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
        isHomeFragment = fragment is HomeFragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainContent, fragment)
            .commit()
    }

    private fun setNavigatorListener() {
        bnvNavigator.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itmBottomNavigationHome -> {
                    loadFragment(homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationExplore -> {
                    loadFragment(exploreFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationMyBooking -> {
                    loadFragment(myBookingFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationSaved -> {
                    loadFragment(savedFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.itmBottomNavigationMyAccount -> {
                    loadFragment(myAccountFragment)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }

}
