package ai.ftech.travelluxury.ui.main.mybooking

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.data.model.history.Booking
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyBookingFragment : Fragment(), MyBookingContract.View {

    private val presenter by lazy {
        MyBookingPresenter().apply {
            view = this@MyBookingFragment
        }
    }

    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter().apply {
            view = this@MyBookingFragment
        }
    }
    private lateinit var rvContent: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.handleHistory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_booking_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvContent = view.findViewById(R.id.rvMyBookingContent)
        rvContent.adapter = adapter
        rvContent.layoutManager = LinearLayoutManager(context)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onHistorySuccess(data: List<Booking>) {
        adapter.dataList = data
        adapter.notifyDataSetChanged()
    }

    override fun onHistoryFail(message: String) {
        Log.d(TAG, "onHistoryFail: $message")
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
