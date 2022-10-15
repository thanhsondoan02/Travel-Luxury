package ai.ftech.travelluxury.ui.setting

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.model.login.AccountData
import ai.ftech.travelluxury.ui.login.LoginActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SettingActivity : AppCompatActivity() {

    interface IListener {
        fun onLogout()
    }

    lateinit var listener: IListener

    private lateinit var rvContent: RecyclerView
    private lateinit var btnGoBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)

        initView()
        initListener()

        // go back
        btnGoBack.setOnClickListener { onBackPressed() }

        // recyclerView
        rvContent.adapter = SettingAdapter().apply {
            view = this@SettingActivity
            listener = this@SettingActivity.listener
        }
        rvContent.layoutManager = LinearLayoutManager(this)
    }

    private fun initView() {
        rvContent = findViewById(R.id.rvSettingContent)
        btnGoBack = findViewById(R.id.btnSettingGoBack)
    }

    private fun initListener() {
        listener = object : IListener {
            override fun onLogout() {

                clearPreferences()
                AccountData.INSTANCE = null

                val intent = Intent(this@SettingActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finishAffinity()
            }
        }
    }

    private fun clearPreferences() {
        val preferences = getSharedPreferences("myPreferences", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

}
