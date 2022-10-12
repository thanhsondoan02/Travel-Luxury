package ai.ftech.travelluxury.ui.main.home

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.TAG
import ai.ftech.travelluxury.ui.hotellist.HotelListActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment(), HomeContract.View {

    interface IListener {
        fun onDomesticClick()
        fun onInternationalClick()
        fun onCityClick()
    }

    val homeAdapter: HomeAdapter

    private lateinit var rvHome: RecyclerView

    private val listener: IListener
    private val presenter: HomePresenter

    init {
        listener = initListener()

        homeAdapter = HomeAdapter().apply {
            listener = this@HomeFragment.listener
        }

        presenter = HomePresenter().apply {
            view = this@HomeFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getDomesticCityList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvHome = view.findViewById(R.id.rlHomeFragment)
        rvHome.layoutManager = LinearLayoutManager(context)
        rvHome.adapter = homeAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetHotelCityListSuccess() {
        homeAdapter.onGetHotelCityListSuccess()
    }

    override fun onGetHotelCityListFail(message: String) {
        Log.d(TAG, "onGetHotelCityListFail: $message")
    }

    private fun initListener(): IListener {
        return object : IListener {
            override fun onDomesticClick() {
                presenter.getDomesticCityList()
            }

            override fun onInternationalClick() {
                Log.d(TAG, "onInternationalClick: ")
                presenter.getInternationalCityList()
            }

            override fun onCityClick() {
                startActivity(Intent(context, HotelListActivity::class.java))
            }
        }
    }
}
