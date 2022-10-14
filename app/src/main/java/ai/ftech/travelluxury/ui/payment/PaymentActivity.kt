package ai.ftech.travelluxury.ui.payment

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.getPriceString
import ai.ftech.travelluxury.data.model.login.AccountData
import ai.ftech.travelluxury.data.model.reserve.ReserveModel
import ai.ftech.travelluxury.ui.payment.method.PaymentMethodActivity
import ai.ftech.travelluxury.ui.payment.success.PaymentSuccessActivity
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity(), PaymentContract.View {

    private lateinit var ivChoosePaymentMethod: TextView
    private lateinit var btnPayNow: Button
    private lateinit var llPaymentMethod: LinearLayout
    private lateinit var tvPaymentNoSelected: TextView
    private lateinit var ivPaymentMethod: ImageView
    private lateinit var tvPaymentMethod: TextView
    private lateinit var btnPaymentBack: ImageButton
    private lateinit var tvPayAs: TextView
    private lateinit var tvPrice: TextView

    private val presenter by lazy {
        PaymentPresenter().apply {
            view = this@PaymentActivity
        }
    }

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

    override fun onPaymentSuccess() {
        startActivity(Intent(this, PaymentSuccessActivity::class.java))
        finishAffinity()
    }

    override fun onPaymentFail() {
        Toast.makeText(this, "Payment failed. Please try again later.", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        ivChoosePaymentMethod = findViewById(R.id.tvPaymentChooseMethod)
        btnPayNow = findViewById(R.id.btnPaymentPayNow)
        llPaymentMethod = findViewById(R.id.llPaymentMethod)
        tvPaymentNoSelected = findViewById(R.id.tvPaymentNoSelected)
        ivPaymentMethod = findViewById(R.id.ivPaymentMethod)
        tvPaymentMethod = findViewById(R.id.tvPaymentMethod)
        btnPaymentBack = findViewById(R.id.btnPaymentBack)
        tvPayAs = findViewById(R.id.tvPaymentPayAs)
        tvPrice = findViewById(R.id.tvPaymentPrice)

        tvPayAs.text = getString(R.string.pay_as) + " " + AccountData.INSTANCE?.email
        tvPrice.text = getPriceString(ReserveModel.INSTANCE.room?.price!!.toInt())
    }

    private fun initListener() {
        ivChoosePaymentMethod.setOnClickListener {
            onChoosePaymentMethodClick()
        }
        btnPayNow.setOnClickListener {
            onPayNowClick()
        }
        btnPaymentBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun onChoosePaymentMethodClick() {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        getContent.launch(intent)
    }

    private fun onPayNowClick() {
        if (llPaymentMethod.visibility == View.VISIBLE) {
            presenter.handlePayment()
        } else {
            Toast.makeText(this, "Please choose payment method", Toast.LENGTH_SHORT).show()
        }
    }

    private fun noPaymentMethodSelected() {
        llPaymentMethod.visibility = View.GONE
        tvPaymentNoSelected.visibility = View.VISIBLE
        btnPayNow.setBackgroundResource(R.color.gray)
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
        btnPayNow.setBackgroundResource(R.color.button_valid)
    }
}
