package ai.ftech.travelluxury.ui.payment.success

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.main.MainActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class PaymentSuccessActivity : AppCompatActivity() {

    private lateinit var btnGoHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_success_activity)

        btnGoHome = findViewById(R.id.tvPaymentSuccessGoHome)
        btnGoHome.setOnClickListener { onGoToHomeScreen() }
    }

    private fun onGoToHomeScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Removes other Activities from stack
        startActivity(intent)
        finishAffinity()
    }

    override fun onBackPressed() {
        // Disable back button
    }

}
