package ai.ftech.travelluxury.main.home

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.hotellist.HotelListActivity
import ai.ftech.travelluxury.model.home.HomeModel.Companion.HOME_MODEL
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var inflateView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        HOME_MODEL.mockData()

        inflateView = inflater.inflate(R.layout.home_fragment, container, false)

        recyclerView = inflateView.findViewById(R.id.rlHomeFragment)

        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        homeAdapter = HomeAdapter().apply {
            listener = object : HomeAdapter.Listener {
                override fun onCityClick() {
                    startActivity(Intent(context, HotelListActivity::class.java))
                }
            }
        }
        recyclerView.adapter = homeAdapter

        return inflateView
    }
}
