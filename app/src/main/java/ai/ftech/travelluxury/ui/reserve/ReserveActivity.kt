package ai.ftech.travelluxury.ui.reserve

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseActivity
import ai.ftech.travelluxury.ui.payment.PaymentActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReserveActivity : BaseActivity(), ReserveAdapter.IListener, IReserveContract.View {

    private lateinit var rvContent: RecyclerView
    private lateinit var btnGoBack: ImageButton

    private val adapter: ReserveAdapter by lazy {
        ReserveAdapter().apply {
            view = this@ReserveActivity
            listener = this@ReserveActivity
        }
    }

    private val presenter by lazy {
        ReservePresenter().apply {
            view = this@ReserveActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()

        // init recycle view
        rvContent.layoutManager = LinearLayoutManager(this)
        rvContent.adapter = adapter

        // go back
        btnGoBack.setOnClickListener {
            finish()
        }
    }

    override fun onContinueClick() {
        showLoading("", "")
        presenter.handleBooking()
    }

    override fun onBookingSuccess() {
        hideLoading()
        startActivity(Intent(this, PaymentActivity::class.java))
    }

    override fun onBookingFail() {
        hideLoading()
        Toast.makeText(this, "Booking failed, please try again later", Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutId(): Int {
        return R.layout.reserve_activity
    }

    private fun initView() {
        rvContent = findViewById(R.id.rvReserveSummary)
        btnGoBack = findViewById(R.id.btnReserveBack)
    }
}
