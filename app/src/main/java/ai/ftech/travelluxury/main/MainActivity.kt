package ai.ftech.travelluxury.main

import ai.ftech.travelluxury.ActionBarView
import ai.ftech.travelluxury.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        val actionBarView = findViewById<ActionBarView>(R.id.actionView)


        actionBarView.setTitle("logout")
    }
}
