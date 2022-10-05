package ai.ftech.travelluxury.ui.reserve

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.payment.PaymentActivity
import ai.ftech.travelluxury.ui.reserve.contact.ContactActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReserveActivity : AppCompatActivity(), ReserveAdapter.IListener {

    private lateinit var rvContent: RecyclerView

    private val adapter: ReserveAdapter by lazy {
        ReserveAdapter().apply {
            listener = this@ReserveActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reserve_activity)

        initView()

        // init recycle view
        rvContent.layoutManager = LinearLayoutManager(this)
        rvContent.adapter = adapter
    }

    override fun onContactClick() {
        startActivity(Intent(this, ContactActivity::class.java))
    }

    override fun onContinueClick() {
        startActivity(Intent(this, PaymentActivity::class.java))
    }

    private fun initView() {
        rvContent = findViewById(R.id.rvReserveSummary)
    }
}
