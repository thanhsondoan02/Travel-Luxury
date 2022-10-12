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

    private lateinit var rvHome: RecyclerView

    private val presenter: HomePresenter by lazy {
        HomePresenter().apply {
            view = this@HomeFragment
        }
    }

    val homeAdapter = HomeAdapter().apply {
        listener = object : HomeAdapter.Listener {
            override fun onCityClick() {
                startActivity(Intent(context, HotelListActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getHotelCityListApi()
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
}
