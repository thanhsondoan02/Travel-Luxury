package ai.ftech.travelluxury.ui.payment

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.payment.method.PaymentMethodActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 0x9345
    }

    private lateinit var ivChoosePaymentMethod: TextView
    private lateinit var btnPayNow: Button
    private lateinit var llPaymentMethod: LinearLayout
    private lateinit var tvPaymentNoSelected: TextView
    private lateinit var ivPaymentMethod: ImageView
    private lateinit var tvPaymentMethod: TextView


    private val getContent: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val paymentMethod =
                    result.data?.getSerializableExtra("paymentMethod") as PAYMENT_METHOD
                updatePaymentMethod(paymentMethod)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_activity)

        initView()
        noPaymentMethodSelected()
        initListener()
    }

    private fun initView() {
        ivChoosePaymentMethod = findViewById(R.id.tvPaymentChooseMethod)
        btnPayNow = findViewById(R.id.btnPaymentPayNow)
        llPaymentMethod = findViewById(R.id.llPaymentMethod)
        tvPaymentNoSelected = findViewById(R.id.tvPaymentNoSelected)
        ivPaymentMethod = findViewById(R.id.ivPaymentMethod)
        tvPaymentMethod = findViewById(R.id.tvPaymentMethod)
    }

    private fun initListener() {
        ivChoosePaymentMethod.setOnClickListener {
            onChoosePaymentMethodClick()
        }
        btnPayNow.setOnClickListener {
            onPayNowClick()
        }
    }

    private fun onChoosePaymentMethodClick() {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        getContent.launch(intent)
    }

    private fun onPayNowClick() {

    }

    private fun noPaymentMethodSelected() {
        llPaymentMethod.visibility = View.GONE
        tvPaymentNoSelected.visibility = View.VISIBLE
    }

    private fun updatePaymentMethod(paymentMethod: PAYMENT_METHOD) {
        llPaymentMethod.visibility = View.VISIBLE
        tvPaymentNoSelected.visibility = View.GONE
        when (paymentMethod) {
            PAYMENT_METHOD.VIETCOMBANK -> {
                ivPaymentMethod.setImageResource(R.drawable.ic_vietcombank)
                tvPaymentMethod.text = getString(R.string.vietcombank)
            }
            PAYMENT_METHOD.TECHCOMBANK -> {
                ivPaymentMethod.setImageResource(R.drawable.ic_techcombank)
                tvPaymentMethod.text = getString(R.string.techcombank)
            }
            PAYMENT_METHOD.MOMO -> {
                ivPaymentMethod.setImageResource(R.drawable.ic_momo)
                tvPaymentMethod.text = getString(R.string.momo_e_wallet)
            }
        }
    }
}
