package ai.ftech.travelluxury.ui.payment.method

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.payment.PAYMENT_METHOD
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class PaymentMethodActivity : AppCompatActivity() {

    private lateinit var mcvVietcombank: MaterialCardView
    private lateinit var mcvTechcombank: MaterialCardView
    private lateinit var mcvMomo: MaterialCardView
    private lateinit var btnClose: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_method_activity)

        initView()
        initListener()
    }

    private fun initView() {
        mcvVietcombank = findViewById(R.id.mcvPaymentMethodVietcombank)
        mcvTechcombank = findViewById(R.id.mcvPaymentMethodTechcombank)
        mcvMomo = findViewById(R.id.mcvPaymentMethodMomo)
        btnClose = findViewById(R.id.btnPaymentMethodClose)
    }

    private fun initListener() {
        mcvVietcombank.setOnClickListener {
            onResultClick(PAYMENT_METHOD.VIETCOMBANK)
        }
        mcvTechcombank.setOnClickListener {
            onResultClick(PAYMENT_METHOD.TECHCOMBANK)
        }
        mcvMomo.setOnClickListener {
            onResultClick(PAYMENT_METHOD.MOMO)
        }
        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun onResultClick(paymentMethod: PAYMENT_METHOD) {
        val intent = Intent()
        intent.putExtra("paymentMethod", paymentMethod)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
